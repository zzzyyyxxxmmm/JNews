package com.wjk32.jnews.entity;

import java.io.Serializable;
import java.util.List;

public class ImageEntity {

    private int totalHits;
    private int total;
    private List<HitsBean> hits;

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<HitsBean> getHits() {
        return hits;
    }

    public void setHits(List<HitsBean> hits) {
        this.hits = hits;
    }

    public static class HitsBean implements Serializable{
        /**
         * largeImageURL : https://pixabay.com/get/ea36b40621f4033ed1584d05fb1d4e9fe474e4d519ac104497f6c07da1eeb3ba_1280.jpg
         * webformatHeight : 409
         * webformatWidth : 640
         * likes : 73
         * imageWidth : 5028
         * id : 3359902
         * user_id : 1767157
         * views : 19065
         * comments : 47
         * pageURL : https://pixabay.com/en/tulips-tulip-field-tulpenbluete-3359902/
         * imageHeight : 3215
         * webformatURL : https://pixabay.com/get/ea36b40621f4033ed1584d05fb1d4e9fe474e4d519ac104497f6c07da1eeb3ba_640.jpg
         * type : photo
         * previewHeight : 95
         * tags : tulips, tulip field, tulpenbluete
         * downloads : 11474
         * user : Capri23auto
         * favorites : 45
         * imageSize : 3090684
         * previewWidth : 150
         * userImageURL : https://cdn.pixabay.com/user/2018/05/07/10-21-46-221_250x250.jpg
         * previewURL : https://cdn.pixabay.com/photo/2018/04/29/12/48/tulips-3359902_150.jpg
         */

        private String largeImageURL;
        private int webformatHeight;
        private int webformatWidth;
        private int likes;
        private int imageWidth;
        private int id;
        private int user_id;
        private int views;
        private int comments;
        private String pageURL;
        private int imageHeight;
        private String webformatURL;
        private String type;
        private int previewHeight;
        private String tags;
        private int downloads;
        private String user;
        private int favorites;
        private int imageSize;
        private int previewWidth;
        private String userImageURL;
        private String previewURL;

        public String getLargeImageURL() {
            return largeImageURL;
        }

        public void setLargeImageURL(String largeImageURL) {
            this.largeImageURL = largeImageURL;
        }

        public int getWebformatHeight() {
            return webformatHeight;
        }

        public void setWebformatHeight(int webformatHeight) {
            this.webformatHeight = webformatHeight;
        }

        public int getWebformatWidth() {
            return webformatWidth;
        }

        public void setWebformatWidth(int webformatWidth) {
            this.webformatWidth = webformatWidth;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public int getImageWidth() {
            return imageWidth;
        }

        public void setImageWidth(int imageWidth) {
            this.imageWidth = imageWidth;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public String getPageURL() {
            return pageURL;
        }

        public void setPageURL(String pageURL) {
            this.pageURL = pageURL;
        }

        public int getImageHeight() {
            return imageHeight;
        }

        public void setImageHeight(int imageHeight) {
            this.imageHeight = imageHeight;
        }

        public String getWebformatURL() {
            return webformatURL;
        }

        public void setWebformatURL(String webformatURL) {
            this.webformatURL = webformatURL;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getPreviewHeight() {
            return previewHeight;
        }

        public void setPreviewHeight(int previewHeight) {
            this.previewHeight = previewHeight;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public int getDownloads() {
            return downloads;
        }

        public void setDownloads(int downloads) {
            this.downloads = downloads;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public int getFavorites() {
            return favorites;
        }

        public void setFavorites(int favorites) {
            this.favorites = favorites;
        }

        public int getImageSize() {
            return imageSize;
        }

        public void setImageSize(int imageSize) {
            this.imageSize = imageSize;
        }

        public int getPreviewWidth() {
            return previewWidth;
        }

        public void setPreviewWidth(int previewWidth) {
            this.previewWidth = previewWidth;
        }

        public String getUserImageURL() {
            return userImageURL;
        }

        public void setUserImageURL(String userImageURL) {
            this.userImageURL = userImageURL;
        }

        public String getPreviewURL() {
            return previewURL;
        }

        public void setPreviewURL(String previewURL) {
            this.previewURL = previewURL;
        }
    }
}

