package ru.prospectgp.test8;

import ru.prospectgp.test8.rss.model.Feed;

public interface FeedConsumer {
    void setFeed(Feed feed);

    void handleError(String massage);
}
