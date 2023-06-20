# StopWatchApp
use Timer class to count time

// create Timer class
Timer timer = new Timer(1000, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      //do sth here
    }
});

// start timer
timer.start();

// stop timer
timer.stop();
