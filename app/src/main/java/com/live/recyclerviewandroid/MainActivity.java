package com.live.recyclerviewandroid;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclrtView,recyclrtView2;
    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> arrayList;
    ArrayList<HashMap<String, String>> finalArrayList;
    RelativeLayout ActivityCall,activityHome;
    //=================================================================
//=================================================================
    BottomNavigationView buttomNav;
    SwipeRefreshLayout swipeRefreshLayout,swipeRefreshLayou2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //================== Create GDPR Class ===========
        //============================================ EN
        itemVideo();
        createFinalItems();


        buttomNav = findViewById(R.id.buttomNav);
        activityHome = findViewById(R.id.activityHome);
        ActivityCall = findViewById(R.id.ActivityCall);

        recyclrtView = findViewById(R.id.recyclrtView);
        recyclrtView2 = findViewById(R.id.recyclrtView2);
        ActivityCall = findViewById(R.id.ActivityCall);
        activityHome = findViewById(R.id.activityHome);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayou2 = findViewById(R.id.swipeRefreshLayout2);


        buttomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId()==R.id.home) {
                ActivityCall.setVisibility(View.GONE);
                    activityHome.setVisibility(View.VISIBLE);
                }else if (menuItem.getItemId()== R.id.call){
                    activityHome.setVisibility(View.GONE);
                    ActivityCall.setVisibility(View.VISIBLE);
                }



                    return false;
            }
        });

        MyAdapter2 myAdapter2= new MyAdapter2();
        recyclrtView2.setAdapter(myAdapter2);
        recyclrtView2.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));


        MyAdapter myAdapter = new MyAdapter();
        Collections.shuffle(arrayList);

        recyclrtView.setAdapter(myAdapter);
        recyclrtView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));



        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {



                createFinalItems();
                MyAdapter myAdapter = new MyAdapter();
                recyclrtView.setAdapter(myAdapter);

                recyclrtView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));



                // Perform your refresh operation here (e.g., load new data)
                // After the refresh operation is complete, call setRefreshing(false) to stop the refresh animation.


                fetchData();
            }
        });
        swipeRefreshLayou2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {



                createFinalItems();

                MyAdapter2 myAdapter2=new MyAdapter2();
                recyclrtView2.setAdapter(myAdapter2);
                recyclrtView2.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

                // Implement your data loading logic here
                // For example, you might fetch new data from a server or a local database.


                fetchData2();

            }
        });


    }
    private void fetchData() {
        // After the data is loaded, stop the refresh animation
        swipeRefreshLayout.setRefreshing(false);
    }
    private void fetchData2() {
        // After the data is loaded, stop the refresh animation
        swipeRefreshLayou2.setRefreshing(false);
    }

    private class MyAdapter2 extends RecyclerView.Adapter{


        int SIMPLE2= 0;
        int ADS2=1;
        private class homepageviewholder extends RecyclerView.ViewHolder{

            TextView titleTv;
            ImageView imageTv;
            LinearLayout layiitemm;
            public homepageviewholder(@NonNull View itemView) {
                super(itemView);

                titleTv= itemView.findViewById(R.id.titleTv);
                imageTv= itemView.findViewById(R.id.imageTv);
                layiitemm= itemView.findViewById(R.id.layiitemm);



            }
        }


        private class nativeadsviewholder extends RecyclerView.ViewHolder{



            public nativeadsviewholder(@NonNull View itemView) {
                super(itemView);



            }
        }


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater= getLayoutInflater();
            if (viewType== SIMPLE2){

                View myview = layoutInflater.inflate(R.layout.simple,parent,false);
                return new MyAdapter2.homepageviewholder(myview);


            }else {

                View myview = layoutInflater.inflate(R.layout.simple,parent,false);
                return new MyAdapter2.nativeadsviewholder(myview);

            }



        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {



            if (getItemViewType(position)==SIMPLE2){
                // MyAdapter.homepageviewholder homepage= (MyAdapter.homepageviewholder) holder;
                MyAdapter2.homepageviewholder homepage = (homepageviewholder) holder;



                hashMap= finalArrayList.get(position);
                String title = hashMap.get("sms");
                String cover_image= hashMap.get("cover_image");
                homepage.titleTv.setText(title);

                Picasso.get().load(cover_image).placeholder(R.drawable.load).into(homepage.imageTv);

                homepage.layiitemm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });








            }else if (getItemViewType(position)==ADS2){
                // MyAdapter.nativeadsviewholder nativview= (MyAdapter.nativeadsviewholder) holder;
                MyAdapter2.nativeadsviewholder nativview = (nativeadsviewholder) holder;

            }

        }

        @Override
        public int getItemCount() {
            return finalArrayList.size();
        }


        @Override
        public int getItemViewType(int position) {


            hashMap= finalArrayList.get(position);
            String type= hashMap.get("type");



            if (type.contains("simple")){
                return SIMPLE2;
            }else if (type.contains("justtry")) {
                return ADS2;
            }


            return position;

        }
    }

    ///===============================================
    ///===============================================
    private class MyAdapter extends RecyclerView.Adapter{

        int SIMPLE= 0;
        int ADS=1;


        private class homepageviewholder extends RecyclerView.ViewHolder{


            TextView name;
            ImageView imageViewitem;
            LinearLayout layItem;
            LottieAnimationView lottieAnimationView;

            public homepageviewholder(@NonNull View itemView) {
                super(itemView);

                name= itemView.findViewById(R.id.name);
                imageViewitem= itemView.findViewById(R.id.imageViewitem);
                layItem= itemView.findViewById(R.id.layItem);
                lottieAnimationView= itemView.findViewById(R.id.lottieAnimationView);

            }
        }


        private class nativeadsviewholder extends RecyclerView.ViewHolder{
            LottieAnimationView lottieAnimationView;



            public nativeadsviewholder(@NonNull View itemView) {
                super(itemView);

                lottieAnimationView= itemView.findViewById(R.id.lottieAnimationView);


            }
        }


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater= getLayoutInflater();
            if (viewType== SIMPLE){

                View myview = layoutInflater.inflate(R.layout.justtry,parent,false);
                return new homepageviewholder(myview);


            }else {

                View myview = layoutInflater.inflate(R.layout.justtry2,parent,false);
                return new nativeadsviewholder(myview);

            }


        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


            if (getItemViewType(position)==SIMPLE){
                homepageviewholder homepage= (homepageviewholder) holder;


                hashMap= finalArrayList.get(position);
                String name= hashMap.get("name");
                String cover_image= hashMap.get("cover_image");
                homepage.name.setText(name);

                homepage.lottieAnimationView.setAnimation(R.raw.video_call);
                homepage.lottieAnimationView.playAnimation();

                homepage.layItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                    }
                });

               // Picasso.get().load(cover_image).placeholder(R.drawable.load).into(homepage.imageViewitem);



            }else if (getItemViewType(position)==ADS){
                nativeadsviewholder nativview= (nativeadsviewholder) holder;
                nativview.lottieAnimationView.setAnimation(R.raw.video_call);
                nativview.lottieAnimationView.playAnimation();

            }

        }

        @Override
        public int getItemCount() {
            return finalArrayList.size();
        }



        @Override
        public int getItemViewType(int position) {


            hashMap= finalArrayList.get(position);
            String type= hashMap.get("type");



            if (type.contains("simple")){
                return SIMPLE;
            }else if (type.contains("justtry")) {
                return ADS;
            }


            return position;

        }


    }
    private void itemVideo() {
        arrayList= new ArrayList<>();

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Olivia Grace");
        hashMap.put("sms", "Hi, how are you doing?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(1).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Emily Rose");
        hashMap.put("sms", "I hope you're doing well.");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(2).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Elizabeth Parker");
        hashMap.put("sms", "How's your day going?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(3).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Sophia Jane");
        hashMap.put("sms", "What have you been up to lately?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(4).jpg");
        arrayList.add(hashMap);


        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Ava Marie ");
        hashMap.put("sms", "Have you been keeping busy?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(5).jpg");
        arrayList.add(hashMap);


        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Mae Thompson");
        hashMap.put("sms", "Any exciting plans for the weekend?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(7).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Kate Anderson");
        hashMap.put("sms", "What's new in your life?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(8).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Mia Grace");
        hashMap.put("sms", "Do you have any hobbies or interests?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(9).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Emma Davis");
        hashMap.put("sms", "Can you recommend a good book or movie?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(10).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Harper Avery ");
        hashMap.put("sms", "How do you like to spend your free time?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(11).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Maria Isabella ");
        hashMap.put("sms", "Are you a morning person or a night owl?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(12).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Luningning Cruz");
        hashMap.put("sms", "What's your favorite type of music?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(13).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Dalisay Reyes");
        hashMap.put("sms", "Do you enjoy traveling?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(15).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Tala Gonzales");
        hashMap.put("sms", "Have you been on any recent trips?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(16).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "What's your dream travel destination?");
        hashMap.put("name", "Liwanag Manalo");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(17).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Sofia Rosalinda");
        hashMap.put("sms", "Tell me about your family.");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(18).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Magdangal Cruz");
        hashMap.put("sms", "Do you have any siblings?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(19).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Teresa Mayum");
        hashMap.put("sms", "Are you close to your parents?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(20).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Dalisay Rivera");
        hashMap.put("sms", "What do you do for a living?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(21).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Lila Sari ");
        hashMap.put("sms", "How did you choose your career?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(22).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Aanya Mehta");
        hashMap.put("sms", "What's the most challenging part of your job?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(23).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Diya Kapoor");
        hashMap.put("sms", "What's the best part of your job?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(24).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Anika Sharma");
        hashMap.put("sms", "Do you like to cook? Any favorite recipes?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(25).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Kavya Patel");
        hashMap.put("sms", "Are you a coffee or tea person?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(26).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Riya Verma");
        hashMap.put("sms", "Do you prefer the city or the countryside?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(27).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Zara Khan");
        hashMap.put("sms", "What's your favorite season and why?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(28).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Meera Joshi");
        hashMap.put("sms", "Have you ever tried any extreme sports?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(29).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Ishika Singh");
        hashMap.put("sms", "What's the most adventurous thing you've done?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(30).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Niharika Reddy");
        hashMap.put("sms", "Do you have any pets?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(31).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Aria Nair");
        hashMap.put("sms", "Tell me about your best friend.");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(32).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "Aanya Mehta Kapoor");
        hashMap.put("name", "What's your favorite childhood memory?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(33).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Diya Sharma Patel");
        hashMap.put("sms", "Are you a sports fan? Which team do you support?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(34).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Anika Singh Verma");
        hashMap.put("sms", "What's the last movie you watched?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(35).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Kavya Reddy Nair");
        hashMap.put("sms", "Do you enjoy going to concerts or live shows?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(36).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Sanjana Choudhary");
        hashMap.put("sms", "Have you ever met a celebrity?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(37).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Tara Sengupta");
        hashMap.put("sms", "What's your idea of a perfect day?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(38).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Shruti Mishra");
        hashMap.put("sms", "Are you into fitness or working out?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(40).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "Do you have any upcoming goals or aspirations?");
        hashMap.put("name", "Pooja Yadav");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(41).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "What's the most beautiful place you've ever been to?");
        hashMap.put("name", "Avani Khanna");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(42).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Amrita Desai");
        hashMap.put("sms", "What's the most beautiful place you've ever been to?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(43).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Ishaanvi Bansal");
        hashMap.put("sms", "How do you handle stress or challenges?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(44).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Ananya Rawat");
        hashMap.put("sms", "Are you a morning person or a night owl?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(45).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Maya Varma");
        hashMap.put("sms", "What's your favorite type of cuisine?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(46).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "Kritika Khurana");
        hashMap.put("name", "Have you ever volunteered or done charity work?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(48).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Tara Iyer");
        hashMap.put("sms", "Do you have any favorite quotes or sayings?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(49).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Kritika Khurana");
        hashMap.put("sms", "How do you stay motivated?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(50).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Anushka Dhawan");
        hashMap.put("sms", "Are you a creative person? Any artistic talents?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(51).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "What's your favorite board game or card game?");
        hashMap.put("name", "Sanya Sen");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(52).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Kiara Malhotra");
        hashMap.put("sms", "Do you like to dance or sing?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(53).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Aashi Mehta");
        hashMap.put("sms", "Have you ever been to a music festival?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(54).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name", "Sofia Santos");
        hashMap.put("type","simple");
        hashMap.put("sms", "What's your favorite type of dessert?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(55).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name", "Gabriela Cruz");
        hashMap.put("type","simple");
        hashMap.put("sms", "Do you believe in astrology or horoscopes?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(56).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Amara Perez");
        hashMap.put("sms", "Are you a spiritual person?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(57).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "What's your favorite type of exercise?");
        hashMap.put("name", "Aria Lim");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(58).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "Do you have a favorite app on your phone?");
        hashMap.put("name", "Clara Manalo");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(59).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "Are you a fan of science fiction or fantasy?");
        hashMap.put("name", "Bianca Reyes");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(60).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "Have you ever been on a road trip?");
        hashMap.put("name", "Teresa Valdez");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(61).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "What's your favorite childhood cartoon?");
        hashMap.put("name", "Ysabella Gonzales");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(62).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "Do you enjoy hiking or camping?");
        hashMap.put("name", "Lila Lim");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(63).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Maria Garcia");
        hashMap.put("sms", "Are you a beach person or a mountain person?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(64).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "What's your favorite type of flower?");
        hashMap.put("name", "Gabriela Magno");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(65).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name", "Amara Cruz");
        hashMap.put("type","simple");
        hashMap.put("sms", "Do you like to take photographs?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(66).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("sms", "Do you like to take");
        hashMap.put("name", "Clara Santos");
        hashMap.put("type","simple");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(67).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "Are you a history buff? Any favorite historical era?");
        hashMap.put("name", "Sofia Garcia");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(68).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "Have you ever been scuba diving or snorkeling?");
        hashMap.put("name", "Bianca Manalo");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(69).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Teresa Alonzo");
        hashMap.put("sms", "What's your favorite way to relax?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(70).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "Do you enjoy gardening?");
        hashMap.put("name", "Ysabella Rivera");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(71).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Lila Magdangal");
        hashMap.put("sms", "What's the most interesting place you've visited?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(72).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Maria Santos");
        hashMap.put("sms", "Are you a morning or evening shower person?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(73).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Gabriela Valdez");
        hashMap.put("sms", "Do you have a favorite type of tea or coffee?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(78).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "Have you ever attended a major sporting event?");
        hashMap.put("name", "Amara Rivera");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(79).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Aria Perez");
        hashMap.put("sms", "What's your favorite type of pizza?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(80).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Clara Reyes");
        hashMap.put("sms", "Are you a fan of science or technology?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(81).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Sofia Magdangal");
        hashMap.put("sms", "Do you enjoy stargazing or astronomy?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(82).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Bianca Alonzo");
        hashMap.put("sms", "Have you ever been to a theme park?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(83).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Aisha Kapoor Verma");
        hashMap.put("sms", "What's the most challenging thing you've done?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(84).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Anvi Singh Mehta");
        hashMap.put("sms", "Do you like to watch documentaries?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(85).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Kyra Patel Iyer");
        hashMap.put("sms", "Are you a fan of comedy or stand-up?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(86).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "Have you ever done any DIY projects?");
        hashMap.put("name", "Diya Reddy Sharma");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(87).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Zara Khanna Kapoor");
        hashMap.put("sms", "What's your favorite type of fruit?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(88).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Meher Gupta Yadav");
        hashMap.put("sms", "Do you like to explore new restaurants?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(89).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Ishita Sharma Joshi");
        hashMap.put("sms", "Are you a fan of spicy food?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(90).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Aaradhya Bansal Malhotra");
        hashMap.put("sms", "Have you ever been to a museum or art gallery?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(92).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "What's your favorite type of chocolate?");
        hashMap.put("name", "Siya Thakur Iyer");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(93).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Avani Reddy Sengupta");
        hashMap.put("sms", "Do you enjoy watching cooking shows?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(94).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Naina Malhotra Varma");
        hashMap.put("sms", "Are you a morning or evening person?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(95).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Aanya Patel Khurana");
        hashMap.put("sms", "What's your favorite type of cheese?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(96).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "Do you have any favorite childhood books?");
        hashMap.put("name", "Sanika Yadav Choudhary");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(98).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Kavya Varma Iyer");
        hashMap.put("sms", "Have you ever been on a cruise?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(99).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Diya Khanna Mehra");
        hashMap.put("sms", "Are you a fan of fashion or clothing design?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(100).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("name", "Arya Sharma Kapoor");
        hashMap.put("sms", "What's your favorite type of ice cream?");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(101).jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","simple");
        hashMap.put("sms", "Do you like to go shopping?");
        hashMap.put("name", "Ishaanvi Rawat Bansal");
        hashMap.put("cover_image", "https://miralive.xyz/photo/a%20(102).jpg");
        arrayList.add(hashMap);


    }
    private void createFinalItems(){
        finalArrayList= new ArrayList<>();

        for (int x=0; x<arrayList.size(); x++){


            if (x>1&& x%1==0){

                Collections.shuffle(arrayList);

                hashMap = new HashMap<>();
                hashMap.put("type", "justtry");
                finalArrayList.add(hashMap);

            }

            hashMap= arrayList.get(x);
            finalArrayList.add(hashMap);

        }


    }




}