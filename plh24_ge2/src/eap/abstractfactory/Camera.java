package eap.abstractfactory;

import eap.simulate.DelaySimulator;

public class Camera {
    private final int MAX_RESOLUTION; // In megapixels (MP). Should be between 10 and 100
    private int selectedResolution;
    private boolean useFlash;

    public Camera(int MAX_RESOLUTION) {
        this.MAX_RESOLUTION = MAX_RESOLUTION;
    }

    public int getMAX_RESOLUTION() {
        return MAX_RESOLUTION;
    }

    public int getSelectedResolution() {
        return selectedResolution;
    }

    public void setSelectedResolution(int selectedResolution) {
        this.selectedResolution = selectedResolution;
    }

    public boolean isUseFlash() {
        return useFlash;
    }

    public void setUseFlash(boolean useFlash) {
        this.useFlash = useFlash;
    }

    @Override
    public String toString() {
        return getMAX_RESOLUTION()+" MP";
    }

    public void takePicture(){
        /*Small delay to simulate taking a picture*/
        DelaySimulator.sleep(2);
        System.out.println("------------------------");
        System.out.println("Taking picture in progress");
        if (useFlash)
            System.out.println("Picture taken with flash at "+selectedResolution+" MP resolution");
        else
            System.out.println("Picture taken without flash at "+selectedResolution+" MP resolution");
        System.out.println("------------------------");
    }
}
