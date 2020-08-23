package com.senlainc.javacourses.petushokvaliantsin.enumeration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GraphProperty {

    public static final String USER_CRED_DEFAULT = "user-cred-all-dependencies";
    public static final String ADVERTISEMENT_PHOTO_DEFAULT = "advertisement-photo-all-dependencies";
    public static final String CHAT_DEFAULT = "chat-all-dependencies";
    public static final String MESSAGE_DEFAULT = "message-all-dependencies";
    public static final String PAYMENT_DEFAULT = "payment-all-dependencies";
    public static final String USER_RATING_DEFAULT = "user-rating-all-dependencies";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Advertisement {
        public static final String DEFAULT = "advertisement-all-dependencies";
        public static final String USER = "advertisement-user-dependency";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AdvertisementComment {
        public static final String DEFAULT = "advertisement-comment-all-dependencies";
        public static final String USER = "advertisement-comment-user-dependency";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class User {
        public static final String DEFAULT = "user-all-dependencies";
        public static final String USER_CRED_AND_RATE = "user-user-cred-and-rate-dependencies";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Type {
        public static final String FETCH = "javax.persistence.fetchgraph";
    }
}
