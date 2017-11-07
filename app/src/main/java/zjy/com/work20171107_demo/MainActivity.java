package zjy.com.work20171107_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Bean bean = new Gson().fromJson(getJson(), Bean.class);
        List<Bean.ApkBean> apk = bean.getApk();
        adapter = new MyAdapter(apk, MainActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
    }

    private String getJson() {
        try {
            InputStream is = getAssets().open("a.json");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte [] b = new byte[1024];
            int len = 0;
            while ((len=is.read(b))!=-1){
                bos.write(b,0,len);
            }
            bos.flush();
            bos.close();
            is.close();
            return bos.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
