package com.senlainc.javacourses.petushokvaliantsin.graph;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GraphName {

    public static final String FETCH_GRAPH_TYPE = "javax.persistence.fetchgraph";
    public static final String USER_DEFAULT = "user-all-dependencies";
    public static final String USER_CRED_DEFAULT = "user-cred-all-dependencies";
    public static final String ADVERTISEMENT_PHOTO_DEFAULT = "advertisement-photo-all-dependencies";
    public static final String CHAT_DEFAULT = "chat-all-dependencies";
    public static final String MESSAGE_DEFAULT = "message-all-dependencies";
    public static final String PAYMENT_DEFAULT = "payment-all-dependencies";

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
}
