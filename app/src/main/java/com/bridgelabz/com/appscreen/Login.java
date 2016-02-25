package com.bridgelabz.com.appscreen;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bridgelabz.com.appscreen.Fragment.ContentFragment;
import com.bridgelabz.com.appscreen.Fragment.NavigationDrawerFragment;
import com.bridgelabz.com.appscreen.Media.VideoPlay;

import com.bridgelabz.com.appscreen.Tbas.SlidingTabLayout;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

/**
 * Created by bridgelabz3 on 20/1/16.
 */
public class Login extends AppCompatActivity {
    private ViewPager mPager;
    SlidingTabLayout mTabs;
    private Toolbar toolbar;
    public static final int CONTENT = 0;
    public static final int VIEW = 1;
    public static final int CONTACTS = 2;
    public static final int SELECT_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mPager = (ViewPager) findViewById(R.id.pager);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



//        NavigationDrawerFragment drawerFragment=(NavigationDrawerFragment)
//                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
//        drawerFragment.setUp((DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp((DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        mTabs.setDistributeEvenly(true);

        mTabs.setViewPager(mPager);

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.plus);
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(imageView)
                .setBackgroundDrawable(R.drawable.button_action_red)
                .build();

        ImageView videoIcon = new ImageView(this);
        videoIcon.setImageResource(R.drawable.video);

        ImageView galleryIcon = new ImageView(this);
        galleryIcon.setImageResource(R.drawable.gallery);

        ImageView audioIcon = new ImageView(this);
        audioIcon.setImageResource(R.drawable.audio);

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        SubActionButton buttonVideo = itemBuilder.setContentView(videoIcon).build();
        SubActionButton buttonGallery = itemBuilder.setContentView(galleryIcon).build();
        SubActionButton buttonAudio = itemBuilder.setContentView(audioIcon).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(buttonVideo)
                .addSubActionView(buttonGallery)
                .addSubActionView(buttonAudio)
                .attachTo(actionButton)
                .build();

        buttonGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent,"select picture"),1);
//                Toast.makeText(Login.this,"Done....",Toast.LENGTH_SHORT).show();
//            }
//        });

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                // ******** code for crop image
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX", 0);
                intent.putExtra("aspectY", 0);
                intent.putExtra("outputX", 200);
                intent.putExtra("outputY", 150);

                try {
                    intent.putExtra("return-data", true);
                    startActivityForResult(Intent.createChooser(intent, "select picture"), SELECT_PICTURE);
                } catch (ActivityNotFoundException e) {

                }
            }
        });


        buttonVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), VideoPlay.class));
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
//            Bundle bundle=new Bundle();
//            bundle.putString(selectedImage.toString(), "key");

//            setContentView(R.layout.media_view);
//
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//            ImageView imageView = (ImageView) findViewById(R.id.imageView);
//            imageView.setImageURI(selectedImage);

            Bundle extras = data.getExtras();
            startActivity(new Intent(this, Add_Content.class).putExtras(extras));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        String[] tabText = getResources().getStringArray(R.array.tabs);

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabText = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment myFragment = null;
            switch (position) {
                case CONTENT:
                    myFragment = ContentFragment.getInstance(position);
                    break;
                case VIEW:
                    myFragment = ViewFragment.getInstance(position);
                    break;
                case CONTACTS:
                    myFragment = ContactsFragment.getInstance(position);
                    break;
            }
            return myFragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabText[position];
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public static class ViewFragment extends Fragment {
        private TextView textView;

        public static ViewFragment getInstance(int position) {
            ViewFragment myFragment1 = new ViewFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            myFragment1.setArguments(args);
            return myFragment1;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View layout = inflater.inflate(R.layout.fragment_view, container, false);
            textView = (TextView) layout.findViewById(R.id.textView);
            Bundle bundle = getArguments();
            if (bundle != null) {
                textView.setText("View Current selected page is: " + bundle.getInt("position"));
            }
            return layout;
        }
    }

    public static class ContactsFragment extends Fragment {
        private TextView textView;

        public static ContactsFragment getInstance(int position) {
            ContactsFragment myFragment2 = new ContactsFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            myFragment2.setArguments(args);
            return myFragment2;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View layout = inflater.inflate(R.layout.fragment_contacts, container, false);
            textView = (TextView) layout.findViewById(R.id.textView);
            Bundle bundle = getArguments();
            if (bundle != null) {
                textView.setText("Contacts Current selected page is: " + bundle.getInt("position"));
            }
            return layout;
        }
    }
}