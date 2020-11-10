package wtf.lars.breakpointnotify.listener;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.xdebugger.XDebugProcess;
import com.intellij.xdebugger.XDebuggerManagerListener;
import org.jetbrains.annotations.NotNull;
import wtf.lars.breakpointnotify.service.NotificationService;

public class DebuggerListener implements XDebuggerManagerListener {

    private final NotificationService NOTIFICATION_SERVICE = new NotificationService();

    @Override
    public void processStarted(@NotNull XDebugProcess debugProcess) {
        final Project originProject = debugProcess.getSession().getProject();

        DataManager.getInstance().getDataContextFromFocusAsync().onSuccess(dataContext -> {
            final Project activeProject = dataContext.getData(PlatformDataKeys.PROJECT);

            if (originProject != activeProject) {
                NOTIFICATION_SERVICE.sendNotification(activeProject, originProject);
            }
        });
    }

}
