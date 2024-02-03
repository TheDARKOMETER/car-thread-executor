interface Engine {

    public void engineStart(Fuel fuel);
    public void engineRev();
    public void engineStop();
    public void consumeFuel(Fuel fuel);
}
