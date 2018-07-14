package vay.enterwind.kesah.entities;

/**
 * Created by novay on 13/07/18.
 */

public class Laporan {

    int id;
    String uuid, label, caption, thumb;

    public Laporan(int id, String uuid, String label, String caption, String thumb) {
        this.id = id;
        this.uuid = uuid;
        this.label = label;
        this.caption = caption;
        this.thumb = thumb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
