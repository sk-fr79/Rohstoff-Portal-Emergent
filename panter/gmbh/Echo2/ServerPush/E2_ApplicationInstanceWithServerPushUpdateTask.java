package panter.gmbh.Echo2.ServerPush;


import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;

//NEU09   -- refactored into this Path
public class E2_ApplicationInstanceWithServerPushUpdateTask 
{
    private List<ActionListener> eventListeners = new LinkedList<ActionListener>();
    private long interval;
    private long nextUpdate;

    
    private boolean   taskIsDead = false;             // kann von aussen gesetzt werden, dann ist das naechste update gleichzeitig das ende
    
    /**
     * Constructor. <br>
     * Note: Setting interval to less than MINIMUM_POLLING_INTERVAL in
     * CustomEcho2Application will have no effect.
     *
     * @param interval the interval between task updates in milliseconds.
     *
     * @see CustomEcho2Application CustomEcho2Application
     */
    public E2_ApplicationInstanceWithServerPushUpdateTask(long interval)
    {
        this.interval = interval;
    }

    /**
     * Update this task. <br>
     * This is called from within CustomEcho2Application and should not be called by
     * the user.
     *
     * @see CustomEcho2Application CustomEcho2Application
     */
    public void update()
    {
        long currentTime = new Date().getTime();
        for ( Iterator iter = eventListeners.iterator(); iter.hasNext(); )
        {
            ((ActionListener)iter.next())
                    .actionPerformed(new ActionEvent(this, ""));
        }
        nextUpdate = currentTime + interval;
        
        if (this.taskIsDead)
        	this.stop();
    }

    /**
     * Start this task. <br>
     * When started, this task's update will be called periodically after each
     * interval.
     */
    public void start()
    {
        nextUpdate = new Date().getTime() + interval;
        E2_ApplicationInstanceWithServerPush.getInstance().addTask(this);
    }

    /**
     * Stop this task.
     */
    public void stop()
    {
       E2_ApplicationInstanceWithServerPush.getInstance().removeTask(this);
    }

    /**
     * Check if this task is ready to be updated. <br>
     * This method is used internally by CustomEcho2Application.
     *
     * @return true if the task is ready.
     * @see CustomEcho2Application CustomEcho2Application
     */
    public boolean isReady()
    {
        return new Date().getTime() >= nextUpdate;
    }

    /**
     * Add a listener to this task.
     *
     * @param listener the listener to add.
     */
    public void addListener(ActionListener listener)
    {
        eventListeners.add(listener);
    }

    /**
     * Remove a listener from this task.
     *
     * @param listener the listener to remove.
     */
    public void removeListener(ActionListener listener)
    {
        eventListeners.remove(listener);
    }

    /**
     * Get the interval between updates.
     *
     * @return the interval in milliseconds.
     */
    public long getInterval()
    {
        return interval;
    }

    /**
     * Set the interval between updtes.
     *
     * @param interval the interval in milliseconds.
     */
    public void setInterval(long interval)
    {
        this.interval = interval;
    }

	public void set_bTaskIsDead(boolean taskIsDead) 
	{
		this.taskIsDead = taskIsDead;
	}

	/**
	 * 2019-07-09
	 * @author martin
	 * @date 09.07.2019
	 *
	 * @return
	 */
	public List<ActionListener> getEventListeners() {
		return eventListeners;
	}

	/**
	 * 
	 * @author martin
	 * @date 09.07.2019
	 *
	 * @return
	 */
	public boolean isTaskIsDead() {
		return taskIsDead;
	}
}
	

