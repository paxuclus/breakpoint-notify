package wtf.lars.breakpointnotify.listeners

import com.intellij.ide.DataManager
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.xdebugger.XDebugProcess
import com.intellij.xdebugger.XDebuggerManagerListener
import wtf.lars.breakpointnotify.services.NotificationService

class DebuggerListener : XDebuggerManagerListener {
    private val NOTIFICATION_SERVICE = NotificationService()
    override fun processStarted(debugProcess: XDebugProcess) {
        val originProject = debugProcess.session.project
        DataManager.getInstance().dataContextFromFocusAsync.onSuccess { dataContext: DataContext ->
            val activeProject = dataContext.getData(PlatformDataKeys.PROJECT)
            if (originProject !== activeProject) {
                NOTIFICATION_SERVICE.sendNotification(activeProject, originProject)
            }
        }
    }
}