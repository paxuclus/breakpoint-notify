package wtf.lars.breakpointnotify.services

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

class NotificationService {
    fun sendNotification(activeProject: Project?, originProject: Project) {
        val content = String.format("Breakpoint reached in other project \"%s\".", originProject.name)
        NotificationGroupManager
            .getInstance()
            .getNotificationGroup("Breakpoint Notify")
            .createNotification(content, NotificationType.INFORMATION)
            .notify(activeProject)
    }
}