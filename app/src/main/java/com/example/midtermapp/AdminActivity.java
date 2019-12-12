package com.example.midtermapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.midtermapp.adapters.BooksAdapter;
import com.example.midtermapp.adapters.DrawerItemCustomAdapter;
import com.example.midtermapp.fragments_admin.Available_books;
import com.example.midtermapp.fragments_admin.BookList;
import com.example.midtermapp.fragments_admin.Profile;
import com.example.midtermapp.model.DataModel;
import com.example.midtermapp.model.book;
import com.example.midtermapp.rest.DividerItemDecorator;
import com.example.midtermapp.rest.RecyclerItemListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    public static ArrayList<book> allBooks = new ArrayList<>();
    private RecyclerView recyclerView;
    private BooksAdapter booksAdapter;

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        setupToolbar();

        DataModel[] drawerItem = new DataModel[3];

        drawerItem[0] = new DataModel(R.drawable.list_all, "All books");
        drawerItem[1] = new DataModel(R.drawable.available, "Available books");
        drawerItem[2] = new DataModel(R.drawable.profile, "Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();



        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        booksAdapter = new BooksAdapter(allBooks);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(booksAdapter);
        createBooks();
        booksAdapter.notifyDataSetChanged();
        recyclerView.addItemDecoration(new DividerItemDecorator(ContextCompat.getDrawable(getApplicationContext(),R.drawable.item_seperator)));
        recyclerView.addOnItemTouchListener(new RecyclerItemListener(getApplicationContext(), recyclerView, new RecyclerItemListener.RecyclerTouchListener() {
            @Override
            public void onClickItem(View v, int position) {
                Toast.makeText(getApplicationContext(),"Clicked: " + allBooks.get(position).getName(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClickItem(View v, int position) {

                Toast.makeText(getApplicationContext(), "Zajatie: " +
                        allBooks.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public void createBooks(){
        allBooks.add(new book(1,"da vinci code", "Dan Brown", "detective", "available"));
        allBooks.add(new book(2,"Martian", "Andy Weir", "science fiction", "available"));
        allBooks.add(new book(3,"1984", "George Orwell", "Dystopian Fiction", "available"));
        allBooks.add(new book(4,"book", "George Orwell", "Dystopian Fiction", "issued"));
    }
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
        @SuppressLint("SetTextI18n")
        private void selectItem(int position) {

            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = new BookList();
                    break;
                case 1:
                    fragment = new Available_books();
                    break;
                case 2:
                    fragment = new Profile();
                    break;

                default:
                    break;
            }
            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                mDrawerList.setItemChecked(position, true);
                mDrawerList.setSelection(position);
                setTitle(mNavigationDrawerItemTitles[position]);
                mDrawerLayout.closeDrawer(mDrawerList);

            } else {
                Log.e("MainActivity", "Error in creating fragment");
            }

        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle(){
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

}
