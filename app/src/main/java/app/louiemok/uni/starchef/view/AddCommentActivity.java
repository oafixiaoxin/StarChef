package app.louiemok.uni.starchef.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.louiemok.uni.starchef.BaseActivity;
import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.adapter.CommentImageAdapter;
import app.louiemok.uni.starchef.seledefine.ImageTools;
import app.louiemok.uni.starchef.seledefine.StarView;
import app.louiemok.uni.starchef.seledefine.YsxRecyclerViewDivider;

public class AddCommentActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private TextView tv_cancel;
    private TextView tv_title;
    private Button btn_commit;
    private StarView ll_star_view;
    private StarView ll_comment_taste;
    private StarView ll_comment_enviroment;
    private StarView ll_comment_serve;
    private TextView tv_star_count;
    private TextView tv_comment_taste;
    private TextView tv_comment_enviroment;
    private TextView tv_comment_serve;
    private TextView tv_tips;
    private LinearLayout ll_comment;
    private EditText et_comment;
    private RecyclerView rv_comment_image;

    CommentImageAdapter commentImageAdapter;

    String shopid;
    public static final int PICK_PIC = 1;
    public static final int CHOOSE_ALBUM = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
    }

    @Override
    public void onResume () {
        super.onResume();
        initElements();
        tv_title.setText(getIntent().getStringExtra("title"));
        shopid = getIntent().getStringExtra("id");
        initStarView();
        initCommentView();
        initEnviroment();
        initServe();
        initCommentImage();
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
        clearEle();
    }

    private void initElements () {
        tv_cancel = (TextView)findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        tv_title = (TextView)findViewById(R.id.tv_title);
        btn_commit = (Button)findViewById(R.id.btn_commit);
        btn_commit.setOnClickListener(this);
        tv_star_count = (TextView)findViewById(R.id.tv_star_count);
        tv_comment_taste = (TextView)findViewById(R.id.tv_comment_taste);
        tv_comment_enviroment = (TextView)findViewById(R.id.tv_comment_enviroment);
        tv_comment_serve = (TextView)findViewById(R.id.tv_comment_serve);
        tv_tips = (TextView)findViewById(R.id.tv_tips);
        ll_comment = (LinearLayout) findViewById(R.id.ll_comment);
        et_comment = (EditText)findViewById(R.id.et_comment);
        et_comment.addTextChangedListener(this);
    }

    private void initStarView () {
        ll_star_view = (StarView)findViewById(R.id.ll_star_view);
        ll_star_view.removeAllViews();
        ll_star_view.setStar(0, R.drawable.star_normal, R.drawable.star_cover, 30, 10);
        ll_star_view.setOnImtemClickListener(new StarView.MyItemClickListener() {
            @Override
            public void click(int position) {
                tv_star_count.setText(""+(position+1)+"星");
                if ( ll_comment.getVisibility() == View.GONE ) {
                    ll_comment.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initCommentView () {
        ll_comment_taste = (StarView)findViewById(R.id.ll_comment_taste);
        ll_comment_taste.removeAllViews();
        ll_comment_taste.setStar(0, R.drawable.smile_gray,R.drawable.smile_cover, 30, 10);
        ll_comment_taste.setOnImtemClickListener(new StarView.MyItemClickListener() {
            @Override
            public void click(int position) {
                tv_comment_taste.setText(getComment(position));
            }
        });
    }

    private void initEnviroment () {
        ll_comment_enviroment = (StarView)findViewById(R.id.ll_comment_enviroment);
        ll_comment_enviroment.removeAllViews();
        ll_comment_enviroment.setStar(0, R.drawable.smile_gray,R.drawable.smile_cover, 30, 10);
        ll_comment_enviroment.setOnImtemClickListener(new StarView.MyItemClickListener() {
            @Override
            public void click(int position) {
                tv_comment_enviroment.setText(getComment(position));
            }
        });
    }

    private void initServe () {
        ll_comment_serve = (StarView)findViewById(R.id.ll_comment_serve);
        ll_comment_serve.removeAllViews();
        ll_comment_serve.setStar(0, R.drawable.smile_gray,R.drawable.smile_cover, 30, 10);
        ll_comment_serve.setOnImtemClickListener(new StarView.MyItemClickListener() {
            @Override
            public void click(int position) {
                tv_comment_serve.setText(getComment(position));
            }
        });
    }

    private void initCommentImage () {
        final List<String> ls = new ArrayList<>();
        ls.add("");
        rv_comment_image = (RecyclerView) findViewById(R.id.rv_comment_image);
        commentImageAdapter = new CommentImageAdapter(this, ls);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4, GridLayout.VERTICAL,
                false);
        rv_comment_image.addItemDecoration(new YsxRecyclerViewDivider(4, dip2px(this, 10), true));
        rv_comment_image.setLayoutManager(gridLayoutManager);
        rv_comment_image.setAdapter(commentImageAdapter);

        commentImageAdapter.setOnItemClickListener(new CommentImageAdapter.MyItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                if ( !ls.get(position).equals("") ) {

                }
                else {
                    if ( ls.size() < 9 ) {
                        if ( ContextCompat.checkSelfPermission(AddCommentActivity.this, Manifest.permission
                                .WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
                            ActivityCompat.requestPermissions(AddCommentActivity.this, new String[]{Manifest
                                    .permission.WRITE_EXTERNAL_STORAGE},1);
                        }
                        else {
                            openAlbum();
                        }
                    }
                    else {
                        Toast.makeText(AddCommentActivity.this, "最多9张图片", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public void openAlbum () {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_ALBUM);
    }

    private void clearEle () {
        tv_cancel = null;
        tv_title = null;
        btn_commit = null;
        ll_star_view = null;
        tv_star_count = null;
    }

    @Override
    public void onRequestPermissionsResult ( int requestCode, String[] permissions, int[]
            grantResults ) {
        switch (requestCode) {
            case 1:
                if ( grantResults.length > 0 && grantResults[0] == PackageManager
                        .PERMISSION_GRANTED ) {
                    openAlbum();
                }
                else {
                    Toast.makeText(this, "You denied the permission.", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data ) {
        switch (requestCode) {
            case CHOOSE_ALBUM:
                if ( resultCode == RESULT_OK ) {
                    try{
                        Uri uri = data.getData();
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
                                .openInputStream(uri));
                        Log.e("bitmap", "压缩前:图片的bitmap大小为:" + bitmap.getByteCount() +
                                ",宽为:" + bitmap.getWidth
                                () + ",高为:" + bitmap.getHeight());
                        bitmap = ImageTools.compressScale(bitmap);
                        Log.e("bitmap", "压缩后:图片的bitmap大小为:" + bitmap.getByteCount() +
                                ",宽为:" + bitmap.getWidth
                                () + ",高为:" + bitmap.getHeight());
//                        iv.setImageBitmap(bitmap);
                    }
                    catch ( Exception ex ) {
                        ex.printStackTrace();
                        Log.e("ex", ex.getMessage());
                        Toast.makeText(this, "crach", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case PICK_PIC:
//                if ( resultCode == RESULT_OK ) {
//                    try{
//                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
//                                .openInputStream(imageUri));
//                        iv.setImageBitmap(bitmap);
//                    }
//                    catch ( Exception ex ) {
//                        ex.printStackTrace();
//                        Log.e("ex", ex.getMessage());
//                        Toast.makeText(this, "crash", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else{
//                    Log.e("error", "error");
//                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick ( View view ) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.btn_commit:
                break;
        }
    }

    private String getComment ( int t ) {
        String taste = "";
        switch (t) {
            case 0:
                taste = "差";
                break;
            case 1:
                taste = "一般";
                break;
            case 2:
                taste = "好";
                break;
            case 3:
                taste = "很好";
                break;
            case 4:
                taste = "非常好";
                break;
        }
        return taste;
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged (CharSequence c, int i1, int i2, int i3) {
        if ( c.length() < 20 ) {
            tv_tips.setText("还差"+(20-c.length())+"个字符,最多150个字符");
        }
        else if ( c.length() >= 20 && c.length() <= 150 ) {
            tv_tips.setText("最多150个字符");
        }
        else {
            tv_tips.setText("已超过"+(c.length()-150)+"个字符");
        }
    }
}
