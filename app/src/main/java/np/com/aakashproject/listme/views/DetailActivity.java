package np.com.aakashproject.listme.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import np.com.aakashproject.listme.R;
import np.com.aakashproject.listme.adapters.CommentsAdapter;
import np.com.aakashproject.listme.models.Comment;
import np.com.aakashproject.listme.models.Post;
import np.com.aakashproject.listme.presenters.DetailPresenter;
import np.com.aakashproject.listme.services.ForumService;

/**
 * Created by Aakash on 11/26/17.
 */
public class DetailActivity extends AppCompatActivity {
    @InjectView(R.id.textViewTitle)
    TextView mTextViewTitle;

    @InjectView(R.id.textViewBody)
    TextView mTextViewBody;

    @InjectView(R.id.listViewComments)
    ListView mListViewComments;

    CommentsAdapter mCommentsAdapter;

    DetailPresenter mDetailPresenter;
    ForumService mForumService;

    protected int mPostId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.inject(this);

        mPostId = getIntent().getIntExtra("postId", 0);

        ArrayList<Comment> dummyComments = new ArrayList<Comment>();
        mCommentsAdapter = new CommentsAdapter(this, dummyComments);
        mListViewComments.setAdapter(mCommentsAdapter);

        mForumService = new ForumService();
        mDetailPresenter = new DetailPresenter(this, mForumService);
        mDetailPresenter.loadComments();
        mDetailPresenter.loadPost();
    }

    public int getPostId() {

        return mPostId;
    }

    public void displayComments(List<Comment> comments) {

        mCommentsAdapter.clear();
        mCommentsAdapter.addAll(comments);
        mCommentsAdapter.notifyDataSetInvalidated();
    }

    public void displayPost(Post post) {

        mTextViewTitle.setText(post.title);
        mTextViewBody.setText(post.body);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
