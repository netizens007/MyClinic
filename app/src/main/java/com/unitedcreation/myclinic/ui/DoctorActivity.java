package com.unitedcreation.myclinic.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.unitedcreation.myclinic.Adapters.DoctorRecyclerAdapter;
import com.unitedcreation.myclinic.R;
import com.unitedcreation.myclinic.SQLiteDatabase.DataContract;
import com.unitedcreation.myclinic.SQLiteDatabase.DataTableHelper;

import static com.unitedcreation.myclinic.utils.StringUtils.PROFILE_EXTRA;
import static com.unitedcreation.myclinic.utils.ViewUtils.switchTheme;

public class DoctorActivity extends AppCompatActivity {
    @BindView(R.id.doctor_logout_button)
    ImageButton mLogout;
    DataTableHelper dataTableHelper;
    @BindView(R.id.doctor_rv)
    RecyclerView doctor_rv;
    @BindView(R.id.tv_doctor_profile)
    TextView mName;

    @BindView(R.id.tv_doctor_licence)
    TextView mLicence;

    @BindView(R.id.tv_doctor_qualification)
    TextView mQualification;
    String time_list[]={"10:10","11:45","12:15","13:00","14:30","15:00"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataTableHelper=new DataTableHelper(this);
        switchTheme(this, 1);
        setContentView(R.layout.activity_doctor);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        doctor_rv.setLayoutManager(layoutManager);

        DoctorRecyclerAdapter adapter=new DoctorRecyclerAdapter(time_list);
        doctor_rv.setAdapter(adapter);
        Cursor cursor=dataTableHelper.getAllData();
        if(cursor.moveToNext()){
            mQualification.setText(cursor.getString(cursor.getColumnIndex(DataContract.DataTable.P_QUALIFICATION)));
            mLicence.setText(cursor.getString(cursor.getColumnIndex(DataContract.DataTable.P_LICENCE)));
            mName.setText("Hi ,"+cursor.getString(cursor.getColumnIndex(DataContract.DataTable.P_NAME)));

        }

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FirebaseAuth.getInstance().signOut();
                dataTableHelper.deleteData();
                Log.i("LOGOUT","LOGOUT");
                finishAndRemoveTask();
            }
        });

    }
}
