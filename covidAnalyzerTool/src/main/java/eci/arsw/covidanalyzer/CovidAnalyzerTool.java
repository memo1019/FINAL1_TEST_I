package eci.arsw.covidanalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Camel Application
 */
public class CovidAnalyzerTool implements Runnable{

    private ResultAnalyzer resultAnalyzer;
    private TestReader testReader;
    private int amountOfFilesTotal;
    private AtomicInteger amountOfFilesProcessed;
    private final int NTHREADS = 5;
    private boolean suspender;
    private ConcurrentLinkedDeque<CovidAnalyzerThread> threads;

    public CovidAnalyzerTool()  {
        resultAnalyzer = new ResultAnalyzer();
        testReader = new TestReader();
        amountOfFilesProcessed = new AtomicInteger();
        threads = new ConcurrentLinkedDeque<>();
        amountOfFilesTotal = -1;
        suspender = false;
    }

    public void processResultData() {
        amountOfFilesProcessed.set(0);
        List<File> resultFiles = getResultFileList();
        amountOfFilesTotal = resultFiles.size();
        int d = amountOfFilesTotal/NTHREADS;
        for(int i=0; i<NTHREADS; i++){
            if (i == NTHREADS-1){
                threads.addLast(new CovidAnalyzerThread(resultFiles,resultAnalyzer, i*d, amountOfFilesTotal-1, amountOfFilesProcessed, testReader));
            }
            else{
                threads.addLast(new CovidAnalyzerThread(resultFiles,resultAnalyzer, i*d, (i*d)+d-1, amountOfFilesProcessed, testReader));
            }
            amountOfFilesProcessed.incrementAndGet();
        }
    }

    private List<File> getResultFileList() {
        List<File> csvFiles = new ArrayList<>();
        try (Stream<Path> csvFilePaths = Files.walk(Paths.get("src/main/resources/")).filter(path -> path.getFileName().toString().endsWith(".csv"))) {
            csvFiles = csvFilePaths.map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvFiles;
    }


    public Set<Result> getPositivePeople() {
        return resultAnalyzer.listOfPositivePeople();
    }

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception
        {
            Thread moneyLaundering = new Thread(new CovidAnalyzerTool());
            moneyLaundering.start();
        }
    /**
     * Método que suspende la ejecución de todos los hilos que se tienen
     * @param hilos Lista con todos los hilos creados para resolver el problema
     */
    public static void pararHilos(ConcurrentLinkedDeque<CovidAnalyzerThread> hilos){
        for (CovidAnalyzerThread hilo: hilos){
            hilo.suspenderHilo();
        }
    }

    /**
     * Método que reanuda la ejecución de todos los hilos que se tienen
     * @param hilos Lista con todos los hilos creados para resolver el problema
     */
    public static void reanudarHilos(ConcurrentLinkedDeque<CovidAnalyzerThread> hilos){
        for (CovidAnalyzerThread hilo: hilos){
            hilo.reanudarHilo();
        }
    }
    @Override
    public void run() {
        Thread processingThread = new Thread(() -> processResultData());
        processingThread.start();
        while(amountOfFilesTotal==-1 || amountOfFilesProcessed.get() < amountOfFilesTotal)
        {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if(line.contains("exit")){
                break;
            }else if(line.isEmpty()){
                if (suspender){
                    reanudarHilos(threads);
                }else{
                    pararHilos(threads);
                    Reporte();
                }
            }else if (!suspender && !line.isEmpty()){
                Reporte();
            }
        }
    }
    private void Reporte(){
        String message = "Processed %d out of %d files.\nFound %d positive people:\n%s";
        Set<Result> positivePeople = getPositivePeople();
        String affectedPeople = positivePeople.stream().map(Result::toString).reduce("", (s1, s2) -> s1 + "\n" + s2);
        message = String.format(message, amountOfFilesProcessed.get(), amountOfFilesTotal, positivePeople.size(), affectedPeople);
        System.out.println(message);
    }

}

