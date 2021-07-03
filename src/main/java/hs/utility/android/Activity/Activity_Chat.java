package hs.utility.android.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
//import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import hs.utility.android.Control.HSChatAdapter;
import hs.utility.android.Enum.HSChatPerson;

/**
 * Created by ParkHongSic on 2016-09-08.
 * "빈 클래스 입니다."
 */
@Deprecated
public class Activity_Chat{// extends Activity {
    /*
    public static ListViewItem person;

    ListView m_ListView;
    HSChatAdapter m_Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(hs.utility.android.R.layout.activity_chat);

        if(getActionBar() != null) {
            ActionBar ab = getActionBar();

            String mHtml = "<font color='#FFFFFF'><b>"+person.kor_name+"</b></font>";
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)  ab.setTitle(Html.fromHtml(mHtml, Html.FROM_HTML_MODE_LEGACY));
            else ab.setTitle(Html.fromHtml(mHtml));

            //ab.setDisplayUseLogoEnabled(false);
            getActionBar().setDisplayHomeAsUpEnabled(true);
            ab.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, hs.utility.android.R.color.colorPrimary)));
            ab.setIcon(getResources().getDrawable(hs.utility.android.R.drawable.icon_chat));//new ColorDrawable(ContextCompat.getColor(this, android.R.color.transparent)));
        }

        // 커스텀 어댑터 생성
        m_Adapter = new HSChatAdapter();

        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(hs.utility.android.R.id.list_chat);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        m_Adapter.add("나", HSChatPerson.ME);
        m_Adapter.add("상대방",HSChatPerson.YOU);
        m_Adapter.add("상대방1",HSChatPerson.YOU);
        m_Adapter.add("나1",HSChatPerson.ME);
        m_Adapter.add("날짜 또는 구분선",HSChatPerson.SEPARATE);
        m_Adapter.add("나 테스트",HSChatPerson.ME);
        m_Adapter.add("상대방 테스트",HSChatPerson.YOU);
        m_Adapter.add("2016/09/09",HSChatPerson.SEPARATE);

        findViewById(hs.utility.android.R.id.btn_chat_sent).setOnClickListener(new Button.OnClickListener() {
                                                          @Override
                                                          public void onClick (View v){
                                                              EditText editText = (EditText) findViewById(hs.utility.android.R.id.txt_chat);
                                                              String inputValue = editText.getText().toString();
                                                              editText.setText("");
                                                              refresh(inputValue, HSChatPerson.ME);
                                                          }
                                                      }
        );
    }

    private void refresh (String inputValue, HSChatPerson _type) {
        m_Adapter.add(inputValue,_type) ;
        m_Adapter.notifyDataSetChanged();
    }*/
}
