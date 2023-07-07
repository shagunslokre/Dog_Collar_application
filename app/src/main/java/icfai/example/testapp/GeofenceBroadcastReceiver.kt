package icfai.example.testapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent

class GeofenceBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //Toast.makeText(context, "Geofence Triggered...", Toast.LENGTH_SHORT).show();
        val geofencingEvent = GeofencingEvent.fromIntent(intent)
        val notificationHelper = NotificationHelper(context)
        if (geofencingEvent.hasError()) {
            Log.d(TAG, "onReceive: Error Receiving Geofence event! ")
            return
        }
        val geofenceList = geofencingEvent.triggeringGeofences
        for (geofence in geofenceList) {
            Log.d(TAG, "onReceive: " + geofence.requestId)
        }
        val transitionType = geofencingEvent.geofenceTransition
        when (transitionType) {
            Geofence.GEOFENCE_TRANSITION_ENTER -> {
                Toast.makeText(context, "GEOFENCE_TRANSITION_ENTER", Toast.LENGTH_SHORT).show()
                notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_ENTER", " ", maps::class.java)
            }
            Geofence.GEOFENCE_TRANSITION_DWELL -> {
                Toast.makeText(context, "GEOFENCE_TRANSITION_DWELL", Toast.LENGTH_SHORT).show()
                notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_DWELL", " ", maps::class.java)
            }
            Geofence.GEOFENCE_TRANSITION_EXIT -> {
                Toast.makeText(context, "GEOFENCE_TRANSITION_EXIT", Toast.LENGTH_SHORT).show()
                notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_EXIT", " ", maps::class.java)
            }
        }
    }

    companion object {
        private const val TAG = "GeofenceBroadcastReceiv"
    }
}