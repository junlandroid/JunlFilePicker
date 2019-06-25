package cn.filepicker.common;

import cn.filepicker.adapter.BaseFileAdapter;
import cn.filepicker.adapter.CommonFileAdapter;
import cn.filepicker.base.FilePickerBaseActivity;
import cn.filepicker.base.FilePickerBaseFragment;

/**
 * Created by cloudist on 2017/7/3.
 */

public class FilePickerCommonActivity extends FilePickerBaseActivity {

    @Override
    public BaseFileAdapter initAdapter() {
        return new CommonFileAdapter(FilePickerCommonActivity.this);
    }

    @Override
    public FilePickerBaseFragment initFragment() {
        return new FilePickerCommonFragment();
    }

}
