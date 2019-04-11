JNews
==================
Jikang Wang
# OverView

# TODO
滑动返回，貌似是要改activity，后期再改


# log
1 在base里创建了baseactivity和naviactivity，naviactivity实现了navigation的效果，这样让MainActivity显得更清爽一点
实现tabview+viewpager
这里有个问题，viewpager.setOffscreenPagLimit(1)会默认多加载前一个和后一个的page，而且不能设置为0，因此不能每次只加载一个页面，这样就会导致多占内存，fragment提供了一个setUserVisbleHint，可以判断是否显示

实现cardview+recyclerview

<img src="https://github.com/zzzyyyxxxmmm/JNews/blob/master/picture/1.png" width="20%" height="20%"><img src="https://github.com/zzzyyyxxxmmm/JNews/blob/master/picture/2.png" width="20%" height="20%">

2  实现了viewpager轮播，搞了一大堆库，下一个目标就是把这些库弄懂，顺便加上自动循环viewpager，原理直接开个线程用handler搞就行了，另外写个presenter，fragment里东西太多了，得搞成MVP


3
实现了MVP架构，很舒服，可以参考官方样例
https://github.com/googlesamples/android-architecture

在presenter里用了Observable.interval实现了轮播效果，但是遇到如下两个问题：
(1)	 点击的时候，应该停止轮播
(2)	翻回去的时候应该从当前页开始继续轮播
解决方案：
	设置ontouchlistener判断是否点击，至于interval停止可以使用disposable.dispose，然后重新绑定
	每次传入当前的position，放到presenter里


4. 昨天一直遇到一个坑，那就是从recyclerview详情页返回后（注意是按actionbar上的回退按钮），之前的activity居然被kill了，查看内存，也没有被杀的迹象，百思不得其解，后来查到

<img src="https://github.com/zzzyyyxxxmmm/JNews/blob/master/picture/3.png" width="60%" height="60%">

目前有两种解决方式：

<img src="https://github.com/zzzyyyxxxmmm/JNews/blob/master/picture/4.png" width="60%" height="60%">

还有种就是改singleTop

但就算这样做了之后，之前的activity没有被re-created，但是没有了动画效果，因为我startactivity的时候是有动画效果的，查看如下信息

<img src="https://github.com/zzzyyyxxxmmm/JNews/blob/master/picture/5.png" width="60%" height="60%">
于是改成这样：

<img src="https://github.com/zzzyyyxxxmmm/JNews/blob/master/picture/6.png" width="60%" height="60%">

OK了，下一步要做的就是，回退后恢复到原来的位置
parcelable=mLayoutManager.onSaveInstanceState();

if(parcelable!=null)
    mLayoutManager.onRestoreInstanceState(parcelable);

稳！

另外解决了一个小bug，就是从详情页跳回来，viewpager轮播的会特别快，显然是之前发送轮播时间的rxjava没有被解绑，于是在fragment里onpause那解绑了一下，一下恢复了，我真棒棒

接下来是美化newsdetail页，目前的状态, 用了webview，总算有点样子，感觉可以用了

<img src="https://github.com/zzzyyyxxxmmm/JNews/blob/master/picture/7.png" width="20%" height="20%"><img src="https://github.com/zzzyyyxxxmmm/JNews/blob/master/picture/8.png" width="20%" height="20%">

5. 用了StaggerGridLayout显示图片，下一步是点击图片放大，并且加上图片的一些参数（点击，评论什么的）
<img src="https://github.com/zzzyyyxxxmmm/JNews/blob/master/picture/9.png" width="20%" height="20%">
图片放大做了很久，一开始是参考https://developer.android.com/training/animation/zoom 上的方法，但这个方法局限性很大，额外加一些操作很难，于是干脆重新开了一个activity，还是用之前的ActivityOptionsCompat.makeSceneTransitionAnimation 来做图片放大转换，与是干脆做了个预加载模糊图片，点开后加载高清图片的功能，一开始是用Picasso加载缩略图，成功后通过回调加载高清图，但这样做的bug是之前加载的缩略图会消失，也就是根本不会产生动画效果，只会加载高清图，解决方案：https://github.com/square/picasso/issues/257

稳

这个imageview应该改成viewpager可以左右滑动，明天加上显示点赞数，放大，长按保存的功能

另外Tab3的功能应该是做一个视频相关的

6. 回家了，继续做
pinch to zoom: https://medium.com/quick-code/pinch-to-zoom-with-multi-touch-gestures-in-android-d6392e4bf52d

图片加载时显示progress，picasso本身没有提供进度的回调，因此需要自己写个类，okhttpclient处理response的时候会有回调
Picasso的总体流程：
总的来说Picasso的流程很简单，当Picasso通过load方法获取图片的时候，需要经过如下步骤才能完成显示图片的流程：
1）将请求封装为Request对象，然后将Request对象进一步封装为Action（ImageAction)对象。
2）将Action(ImageAction)对象交给Dispather进行分发
3）最终将action交给BitmapHunter这个Runnable作为在线程池中线程的工作的单元（具体的是讲action持有的当前Reqeuest对象）
4）由RequestHandler来处理当前request,调用其load方法将加载完成后的图片交给PicassoDrawable显示图片。
代码流程如下：Picasso->load->创建request->创建action->Dispatcher分发action->RequestHandler的load方法处理具体的请求->PicassoDrawable显示图片。

另外写了PieImageView用于显示progress

