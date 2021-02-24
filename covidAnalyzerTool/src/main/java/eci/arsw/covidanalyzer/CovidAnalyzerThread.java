package eci.arsw.covidanalyzer;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CovidAnalyzerThread extends Thread{

        private boolean suspender;
        private List<File> resultFiles;
        private TestReader testReader;
        private int a;
        private int b;
        private ResultAnalyzer resultAnalyzer;
        private AtomicInteger amountOfFilesProcessed;


        public CovidAnalyzerThread( List<File> resultFiles,ResultAnalyzer resultAnalyzer, int a, int b,AtomicInteger amountOfFilesProcessed, TestReader testReader) {
            this.resultFiles=resultFiles;
            this.testReader=testReader;
            this.resultAnalyzer=resultAnalyzer;
            this.amountOfFilesProcessed=amountOfFilesProcessed;
            this.a=a;
            this.b=b;
            this.suspender = false;
        }

        @Override
        public void run() {
            for(File transactionFile : resultFiles){
                synchronized(this){
                    while(suspender){
                        try {
                            wait();
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                List<Result> results = testReader.readResultsFromFile(transactionFile);
                for(Result result : results)
                {
                    resultAnalyzer.addResult(result);
                }
                amountOfFilesProcessed.incrementAndGet();
            }
        }


        public synchronized void suspenderHilo() {
            suspender = true;
        }

        /**
         * Método que reanuda el hilo
         */
        public synchronized void reanudarHilo() {
            suspender = false;
            synchronized(this){
                notifyAll();
            }
        }
}

