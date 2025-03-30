package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "group_list")
public class GroupRecord {

    @Id
    @Column(name = "group_id")
    public int group_id;

    @Column(name = "group_name")
    public String name;

    @Column(name = "group_header")
    public String header;

    @Column(name = "group_footer")
    public String footer;

    public Date deprecated = new Date();

    public GroupRecord() {
    }

    public GroupRecord(int group_id, String name, String header, String footer) {
        this.group_id = group_id;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }
}
