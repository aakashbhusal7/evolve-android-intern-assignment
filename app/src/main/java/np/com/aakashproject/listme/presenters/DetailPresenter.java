package np.com.aakashproject.listme.presenters;


import java.util.List;

import np.com.aakashproject.listme.models.Comment;
import np.com.aakashproject.listme.models.Post;
import np.com.aakashproject.listme.services.ForumService;
import np.com.aakashproject.listme.views.DetailActivity;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Aakash on 11/26/17.
 */

public class DetailPresenter {

    DetailActivity mView;
    ForumService mForum;

    public DetailPresenter(DetailActivity activity, ForumService forum) {

        mView = activity;
        mForum = forum;
    }

    public void loadPost() {
        mForum.getApi()
                .getPost(mView.getPostId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Post post) {

                        mView.displayPost(post);
                    }
                });
    }
    public void loadComments() {

        mForum.getApi()
                .getComments(mView.getPostId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Comment>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Comment> comments) {

                        mView.displayComments(comments);
                    }
                });
    }
}
