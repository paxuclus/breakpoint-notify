package wtf.lars.breakpointnotify.service;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class NotificationService {

    private final NotificationGroup NOTIFICATION_GROUP = new NotificationGroup(
            "Breakpoint Notify",
            NotificationDisplayType.BALLOON,
            true
    );

    public void sendNotification(Project activeProject, @NotNull Project originProject) {
        final String content = String.format("Breakpoint reached in other project \"%s\".", originProject.getName());
        final Notification notification = NOTIFICATION_GROUP.createNotification(content, NotificationType.INFORMATION);
        notification.notify(activeProject);
    }

}
