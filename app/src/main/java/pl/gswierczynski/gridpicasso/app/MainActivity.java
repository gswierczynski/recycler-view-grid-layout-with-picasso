package pl.gswierczynski.gridpicasso.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv);

            rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            rv.setItemAnimator(new DefaultItemAnimator());
            rv.setAdapter(new ImageAdapter());

            return rootView;
        }
    }

    private static class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> implements ClickableViewHolder.OnClickListener {

        public static final String TAG = "ImageAdapter";
        List<Integer> resourceIds = Arrays.asList(
                R.drawable.a0,
                R.drawable.a1,
                R.drawable.a2,
                R.drawable.a3,
                R.drawable.a4,
                R.drawable.a5,
                R.drawable.a6,
                R.drawable.a7,
                R.drawable.a8,
                R.drawable.a9,
                R.drawable.a10,
                R.drawable.a11,
                R.drawable.a12,
                R.drawable.a13,
                R.drawable.a14,
                R.drawable.a15,
                R.drawable.a16,
                R.drawable.a17,
                R.drawable.a18,
                R.drawable.a19,
                R.drawable.a20);

        public ImageAdapter() {
            setHasStableIds(true);
        }

        @Override
        public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new ImageViewHolder(v, this);
        }

        @Override
        public void onBindViewHolder(ImageViewHolder holder, int position) {
            Log.d(TAG, "onBindViewHolder position: " + position + " | holder obj:" + holder.toString());
            Picasso.with(holder.iv.getContext())
                    .load(resourceIds.get(position))
                    .fit()
                    .centerInside()
                    .into(holder.iv);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return resourceIds.size();
        }

        @Override
        public void onClick(View view, int position) {
            Log.d(TAG, "onClick position: " + position);
            notifyItemChanged(position);
        }

        @Override
        public boolean onLongClick(View view, int position) {
            return false;
        }
    }

    private static class ImageViewHolder extends ClickableViewHolder {

        public ImageView iv;

        public ImageViewHolder(View itemView, OnClickListener onClickListener) {
            super(itemView, onClickListener);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
