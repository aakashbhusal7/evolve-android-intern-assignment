package np.com.aakashproject.listme.presenters;

import java.util.List;

import np.com.aakashproject.listme.models.Post;
import np.com.aakashproject.listme.services.ForumService;
import np.com.aakashproject.listme.views.ListActivity;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Aakash on 11/26/17.
 */
public class ListPresenter {
    ListActivity mView;
    ForumService mForum;

    public ListPresenter(ListActivity view, ForumService forum) {

        mView = view;
        mForum = forum;
    }

    public void loadPosts() {

        mForum.getApi()
                .getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Post> posts) {

                        mView.displayPosts(posts);
                    }
                });
    }
}
