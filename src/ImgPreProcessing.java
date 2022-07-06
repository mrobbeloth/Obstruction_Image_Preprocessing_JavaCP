import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.MatExpr;
import org.bytedeco.javacpp.indexer.UByteRawIndexer;

import static org.bytedeco.opencv.global.opencv_core.CV_8U;

public class ImgPreProcessing {
    /**
     * This method documents how to access and print individual
     * elements of OpenCV matrix being access using JavaCV
     */
    public static void test1() {
        MatExpr mat = Mat.eye(3, 3, CV_8U);
        Mat finalMat = mat.asMat();
        System.out.println("mat = " + finalMat.toString());
        // creating the indexer is a recent bit of magic on their part
        UByteRawIndexer bri = finalMat.createIndexer();
        int tRows = finalMat.rows();
        int tCols = finalMat.cols();
        for(int i = 0; i < tRows; i++) {
            for (int j = 0; j < tCols; j++) {
                System.out.print(bri.get(i,j));
            }
            System.out.println();
        }
    }

    /**
     * Will need CUDA support built into refernced OpenCV libraries
     */
    public static void test2() {
        MatExpr mat = Mat.eye(100, 100, CV_8U);
        Mat finalMat = mat.asMat();
        GpuMat dstMat = new GpuMat();
        dstMat.upload(finalMat);
        System.out.println("mat = " + dstMat.toString());
        // creating the indexer is a recent bit of magic on their part
        Mat dwnLoadMat = new Mat();
        dstMat.download(dwnLoadMat);
        UByteRawIndexer bri = dwnLoadMat.createIndexer();
        int tRows = finalMat.rows();
        int tCols = finalMat.cols();
        for(int i = 0; i < tRows; i++) {
            for (int j = 0; j < tCols; j++) {
                System.out.print(bri.get(i,j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        test2();
    }
}
