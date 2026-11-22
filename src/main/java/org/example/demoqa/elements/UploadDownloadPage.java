package org.example.demoqa.elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.demoqa.common.BasePage;

import java.nio.file.Path;

public class UploadDownloadPage extends BasePage {
    public final Locator uploadInput;
    public final Locator downloadLink;
    public final Locator uploadedFilePath;

    public UploadDownloadPage(Page page) {
        super(page);
        uploadInput = page.locator("#uploadFile");
        downloadLink = page.getByRole(com.microsoft.playwright.options.AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Download"));
        uploadedFilePath = page.locator("#uploadedFilePath");
    }

    public void upload(Path file) { uploadInput.setInputFiles(file); }
    public String getUploadedFilePath() { return textOf(uploadedFilePath); }
    public void download() {
        page.waitForDownload(downloadLink::click);
    }
}
