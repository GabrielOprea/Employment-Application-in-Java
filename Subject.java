public interface Subject {
    void addObserver(User user);
    void removeObserver(User c);
    void notifyAllObservers(Notification notification);
    void notifyObserver(Observer o, Notification notification);
}
