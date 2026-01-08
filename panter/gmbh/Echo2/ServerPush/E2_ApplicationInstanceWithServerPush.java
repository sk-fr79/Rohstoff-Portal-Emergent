package panter.gmbh.Echo2.ServerPush;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.TaskQueueHandle;
import nextapp.echo2.webcontainer.ContainerContext;


public abstract class E2_ApplicationInstanceWithServerPush extends ApplicationInstance 
{

	  /**
     * The smallest polling interval allowed. Anything less than this gets set
     * to the minimum.
     * Note: To ensure smooth operation, don't set this to less than 500.
     */
    private static final int MINIMUM_POLLING_INTERVAL = 1000;

    private TaskQueueHandle taskQueue;
    private Set<E2_ApplicationInstanceWithServerPushUpdateTask> updateTasks = new HashSet<E2_ApplicationInstanceWithServerPushUpdateTask>();

    /**
     * Update the polling interval for server push. <br>
     * This sets the time between polls to the server when there are update
     * tasks running to the GCD of all active tasks intervals.
     */
    private void updatePollingInterval()
    {
        TreeSet<Long> values = new TreeSet<Long>();
        for ( Iterator iter = updateTasks.iterator(); iter.hasNext(); )
        {
        	E2_ApplicationInstanceWithServerPushUpdateTask task = (E2_ApplicationInstanceWithServerPushUpdateTask)iter.next();
            values.add(new Long(task.getInterval()));
        }
        int pollingInterval = (int)gcd(values);
        if ( pollingInterval < MINIMUM_POLLING_INTERVAL )
        {
            pollingInterval = MINIMUM_POLLING_INTERVAL;
        }
        ContainerContext containerContext = (ContainerContext)getContextProperty(ContainerContext.CONTEXT_PROPERTY_NAME);
        containerContext.setTaskQueueCallbackInterval(taskQueue, pollingInterval);
    }

    /**
     * Add an update task to the active task list. <br>
     * This method is used by UpdateTask and should not be called by the user.
     *
     * @param task the task to add.
     */
    public void addTask(E2_ApplicationInstanceWithServerPushUpdateTask task)
    {
        // Build the task queue if necessary
        if ( null == taskQueue )
        {
            taskQueue = createTaskQueue();
        }
        updateTasks.add(task);
        updatePollingInterval();
    }

    /**
     * Remove a task from the active task list. <br>
     * This method is used by UpdateTask and should not be called by the user.
     *
     * @param task the task to remove.
     */
    public void removeTask(E2_ApplicationInstanceWithServerPushUpdateTask task)
    {
        updateTasks.remove(task);
        // Remove the task queue if there are no update tasks left.
        if ( updateTasks.size() == 0 )
        {
            if ( taskQueue != null )
            {
                removeTaskQueue(taskQueue);
                taskQueue = null;
            }
        }
        else
        {
            updatePollingInterval();
        }
    }

    /**
     * Runnable implementation to update a task.
     */
    private class UpdateTaskRunnable implements Runnable
    {
    	E2_ApplicationInstanceWithServerPushUpdateTask task;

        public UpdateTaskRunnable(E2_ApplicationInstanceWithServerPushUpdateTask task)
        {
            this.task = task;
        }

        public void run()
        {
            task.update();
        }
    }

    /**
     * Override hasQueuedTasks to send out updates. <br>
     * Hacking a status check method and adding side effects is ugly, but
     * it's the way recommended by the Echo2 team.
     * It's not harmful so long as the underlying implementation only calls
     * this method once per polling interval. <br>
     * Check each task, and if it is ready to be updated, schedule an update.
     */
    public boolean hasQueuedTasks()
    {
        for ( Iterator iter = updateTasks.iterator(); iter.hasNext(); )
        {
        	E2_ApplicationInstanceWithServerPushUpdateTask task = (E2_ApplicationInstanceWithServerPushUpdateTask)iter.next();
            if ( task.isReady() )
            {
                enqueueTask(taskQueue, new UpdateTaskRunnable(task));
            }
        }
        return super.hasQueuedTasks();
    }

    /**
     * Get the current application.
     * UpdateTask uses this to call addTask() and removeTask().
     *
     * @return the current application
     */
    public static E2_ApplicationInstanceWithServerPush getInstance()
    {
        return (E2_ApplicationInstanceWithServerPush)getActive();
    }

    /**
     * Compute the greatest common divisor of a set of positive values
     *
     * @param values the values
     * @return the gcd
     */
    public static long gcd(TreeSet values)
    {
        // Get the smallest value
        long smallestValue = ((Long)values.iterator().next()).longValue();

        if ( smallestValue <= 1 )
        {
            return 1;
        }

        // Find GCD
        for ( long divisor = smallestValue; divisor > 1; divisor-- )
        {
            boolean divisible = true;
            for ( Iterator iter = values.iterator(); iter.hasNext(); )
            {
                long value = ((Long)iter.next()).longValue();
                divisible = value % divisor == 0;
                if ( !divisible )
                {
                    break;
                }
            }
            if ( divisible )
            {
                return divisor;
            }
        }
        return 1;
    }
	
	
	

}
