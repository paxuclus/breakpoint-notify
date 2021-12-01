package wtf.lars.breakpointnotify.service;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class NotificationService {

    public void sendNotification(Project activeProject, @NotNull Project originProject) {
        final String content = String.format("Breakpoint reached in other project \"%s\".", originProject.getName());

        NotificationGroupManager
                .getInstance()
                .getNotificationGroup("Breakpoint Notify")
                .createNotification(content, NotificationType.INFORMATION)
                .notify(activeProject);
    }

}
