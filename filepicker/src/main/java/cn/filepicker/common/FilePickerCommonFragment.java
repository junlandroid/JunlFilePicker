package cn.filepicker.common;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.filepicker.R;
import cn.filepicker.adapter.BaseFileAdapter;
import cn.filepicker.adapter.CommonFileAdapter;
import cn.filepicker.base.FilePickerBaseActivity;
import cn.filepicker.base.FilePickerBaseFragment;
import cn.filepicker.event.FileRefreshEvent;
import cn.filepicker.model.FileItem;

/**
 * Created by cloudist on 2017/7/3.
 */

public class FilePickerCommonFragment extends FilePickerBaseFragment {

    private int mMaxCount = 9;
    private ImageView imageView;

    @Override
    public BaseFileAdapter.OnClickListener initClickListener() {
        return new CommonFileAdapter.OnClickListener() {
            @Override
            public void onClick(View view, final FileItem fileItem) {
                switch (fileItem.getItemType()) {
                    case CommonFileAdapter.TYPE_DOC:
                        imageView = (ImageView) view.findViewById(R.id.cb_choose);
                        if (mType == TYPE_SELECTED) {
                            EventBus.getDefault().post(new FileRefreshEvent());
                        } else if (mType == TYPE_ROOT || mType == TYPE_DIRECTORY) {
                        } else {
                        }
                        boolean filesChange = ((FilePickerBaseActivity) getActivity()).selectedFilesChange(fileItem);
                        if (filesChange) {
                            updateSelectButton(fileItem);
                            updateCommitButton();
                        } else {
                            Toast.makeText(getActivity(),"最多选择9个文件", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case CommonFileAdapter.TYPE_FOLDER:
                        goIntoDirectory(fileItem);
                        break;
                }
            }
        };
    }

    private void updateSelectButton(FileItem fileItem) {
        List<FileItem> selectedFiles = ((FilePickerBaseActivity) getActivity()).selectedFiles;
        if (selectedFiles.contains(fileItem)) {
            imageView.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.icon_image_checked));
        } else {
            imageView.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.icon_image_check));
        }
    }

    private void updateCommitButton() {
        //改变确定按钮UI
        int selectCount = ((FilePickerBaseActivity) getActivity()).selectedFiles.size();
        if (selectCount == 0) {
            toolbarSubmit.setEnabled(false);
            toolbarSubmit.setText(getString(R.string.confirm));
            return;
        }
        if (selectCount < mMaxCount) {
            toolbarSubmit.setEnabled(true);
            toolbarSubmit.setText(String.format(getString(R.string.selected), selectCount, mMaxCount));
            return;
        }
        if (selectCount == mMaxCount) {
            toolbarSubmit.setEnabled(true);
            toolbarSubmit.setText(String.format(getString(R.string.selected), selectCount, mMaxCount));
            return;
        }
    }
}
