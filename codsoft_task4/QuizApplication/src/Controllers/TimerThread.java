package Controllers;

import Models.QuizModel;
import View.QuizView;

import java.util.concurrent.CountDownLatch;
class TimerThread extends Thread {
    private int seconds;
    private final int questionCounter;
    public CountDownLatch latch;
    public boolean running = true;
    public TimerThread(int seconds, int questionCounter, CountDownLatch latch) {
        this.seconds = seconds;
        this.questionCounter = questionCounter;
        this.latch = latch;
    }
    public void run() {
        try {
            // Wait for the latch to countdown
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (seconds >= 0 && running) {
            QuizView.displayTimer(questionCounter+1, seconds);
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            seconds--;

            if(seconds == 0){
                // Timer has ended, the quiz taker didn't answer
                QuizController.answerIsWritten = false;
                QuizView.timeIsUp(questionCounter + 1);
                QuizModel.QuizTakerAnswers.add("");
                QuizView.enterNext();
                // Stop the timer
                stopTimer();
            }
        }
    }
    public void stopTimer() {running = false;}
    public int getSeconds() {
        return seconds;
    }
}
