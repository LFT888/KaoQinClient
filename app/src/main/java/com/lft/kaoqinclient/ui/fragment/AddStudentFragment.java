package com.lft.kaoqinclient.ui.fragment;


import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.lft.kaoqinclient.R;
import com.lft.kaoqinclient.aop.SingleClick;
import com.lft.kaoqinclient.common.MyActivity;
import com.lft.kaoqinclient.common.MyFragment;
import com.lft.kaoqinclient.http.model.HttpData;
import com.lft.kaoqinclient.http.request.AddStudentByClassApi;
import com.lft.kaoqinclient.http.request.AddStudentByIdApi;
import com.lft.kaoqinclient.http.request.TeacherAddCourseApi;
import com.lft.kaoqinclient.http.response.StudentInfoBean;
import com.lft.kaoqinclient.http.response.TeacherCourseBean;
import com.lft.kaoqinclient.ui.dialog.InputDialog;

import java.util.List;

/**
 * TODO
 * author LFT
 *
 * @date 2021/2/21 23:16
 */
public class AddStudentFragment extends MyFragment<MyActivity>  {

    private AddStudentCallBack callBackInterface;

    private Button mAddByFile;
    private Button mAddByClass;
    private Button mAddById;
    private List<StudentInfoBean> list;


    public static AddStudentFragment newInstance(List<StudentInfoBean> list){
        return new AddStudentFragment(list);
    }

    public AddStudentFragment(List<StudentInfoBean> list){
        super();
        this.list = list;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_student;
    }

    @Override
    protected void initView() {
        mAddByFile = findViewById(R.id.btn_add_student_by_file);
        mAddByClass = findViewById(R.id.btn_add_student_by_class);
        mAddById = findViewById(R.id.btn_add_student_by_id);

        setOnClickListener(mAddByFile, mAddByClass, mAddById);
    }

    @Override
    protected void initData() {

    }

    @SingleClick
    @Override
    public void onClick(View v) {
        if (v == mAddByClass){

            new InputDialog.Builder(getActivity())
                    .setTitle(getString(R.string.class_name))
                    .setListener((dialog, content) -> {
                        findStudentByClass(content);
                    })
                    .show();

        }
        else if (v == mAddById){

            new InputDialog.Builder(getActivity())
                    .setTitle(getString(R.string.common_id_input_hint))
                    .setListener((dialog, content) -> {
                        findStudentById(content);
                    })
                    .show();

        }

    }

    private void addStudent(List<StudentInfoBean> param){
        if (param != null && param.size() != 0){
            list.addAll(param);
            callBackInterface.getStudentList();
        }
    }

    private void addStudent(StudentInfoBean param){
        if (param != null){
            list.add(param);
            callBackInterface.getStudentList();
        }

    }

    private void findStudentByClass(String name){

        EasyHttp.get(this)
                .api(new AddStudentByClassApi()
                        .setClassName(name))
                .request(new HttpCallback<HttpData<List<StudentInfoBean>>>(this) {
                    @Override
                    public void onSucceed(HttpData<List<StudentInfoBean>> data) {
                        if (data.getData()!=null && data.getData().size() != 0){
                            addStudent(data.getData());
                            toast("添加" + data.getData().size() + "名学生");
                        }

                    }
                });
    }

    private void findStudentById(String sid){

        EasyHttp.get(this)
                .api(new AddStudentByIdApi()
                        .setSid(sid))
                .request(new HttpCallback<HttpData<StudentInfoBean>>(this) {
                    @Override
                    public void onSucceed(HttpData<StudentInfoBean> data) {
                        if (data.getData()!=null){
                            addStudent(data.getData());
                            toast("添加成功");
                        }
                    }
                });
    }
    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        ///获取绑定的监听
        if (context instanceof AddStudentCallBack) {
            callBackInterface = (AddStudentCallBack) context;
        }

    }

    public interface AddStudentCallBack {
        void getStudentList();
    }


}
