//package models;
//
//import org.hibernate.annotations.Type;
//import org.hibernate.engine.jdbc.BinaryStream;
//
//import javax.persistence.*;
//import java.io.InputStream;
//import java.util.Arrays;
//import java.util.Objects;
//
//@Table(name = "files")
//public class File {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int file_id;
//
//    @ManyToOne
//    @JoinColumn(name = "event_fk", referencedColumnName = "e_id")
//    private Event event;
//
//    @OneToOne
//    @JoinColumn(name = "uploader_id", referencedColumnName = "u_id")
//    private User uploader_id;
//
//    @OneToOne
//    @JoinColumn(name = "requirer_id", referencedColumnName = "u_id")
//    private User requirer_id;
//
//    private String filename;
//
//    @Lob
//    @Type(type = "org.hibernate.type.BinaryType")
//    private byte[] file;
//
//
//    public File() {
//    }
//
//    public File(int file_id, Event event, User uploader_id, User requirer_id, String filename, byte[] file) {
//        this.file_id = file_id;
//        this.event = event;
//        this.uploader_id = uploader_id;
//        this.requirer_id = requirer_id;
//        this.filename = filename;
//        this.file = file;
//    }
//
//    public int getFile_id() {
//        return file_id;
//    }
//
//    public void setFile_id(int file_id) {
//        this.file_id = file_id;
//    }
//
//    public Event getEvent() {
//        return event;
//    }
//
//    public void setEvent(Event event) {
//        this.event = event;
//    }
//
//    public User getUploader_id() {
//        return uploader_id;
//    }
//
//    public void setUploader_id(User uploader_id) {
//        this.uploader_id = uploader_id;
//    }
//
//    public User getRequirer_id() {
//        return requirer_id;
//    }
//
//    public void setRequirer_id(User requirer_id) {
//        this.requirer_id = requirer_id;
//    }
//
//    public String getFilename() {
//        return filename;
//    }
//
//    public void setFilename(String filename) {
//        this.filename = filename;
//    }
//
//    public byte[] getFile() {
//        return file;
//    }
//
//    public void setFile(byte[] file) {
//        this.file = file;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        File file1 = (File) o;
//        return getFile_id() == file1.getFile_id() && Objects.equals(getEvent(), file1.getEvent()) && Objects.equals(getUploader_id(), file1.getUploader_id()) && Objects.equals(getRequirer_id(), file1.getRequirer_id()) && Objects.equals(getFilename(), file1.getFilename()) && Arrays.equals(getFile(), file1.getFile());
//    }
//
//    @Override
//    public int hashCode() {
//        int result = Objects.hash(getFile_id(), getEvent(), getUploader_id(), getRequirer_id(), getFilename());
//        result = 31 * result + Arrays.hashCode(getFile());
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return "File{" +
//                "file_id=" + file_id +
//                ", event=" + event +
//                ", uploader_id=" + uploader_id +
//                ", requirer_id=" + requirer_id +
//                ", filename='" + filename + '\'' +
//                ", file=" + Arrays.toString(file) +
//                '}';
//    }
//}
