package edu.byu.cs.tweeter.client.model.service;

import edu.byu.cs.tweeter.client.backgroundTask.GetFeedTask;
import edu.byu.cs.tweeter.client.backgroundTask.PagedTaskData;
import edu.byu.cs.tweeter.client.model.service.handlers.PagedTaskHandler;
import edu.byu.cs.tweeter.model.domain.Status;

public class FeedService extends Service {
    public static final String GET_FEED_URL_PATH = "getfeed";

    public void getFeed(PagedTaskData<Status> data) {
        GetFeedTask getFeedTask = new GetFeedTask(data, new GetFeedHandler(data.getObserver()));
        executeTask(getFeedTask);
    }

    /**
     * Message handler (i.e., observer) for GetFeedTask.
     */
    private class GetFeedHandler extends PagedTaskHandler<Service.PagedObserver<Status>, Status> {
        public GetFeedHandler(Service.PagedObserver<Status> inObs) { super(inObs, "get feed"); }
    }
}
