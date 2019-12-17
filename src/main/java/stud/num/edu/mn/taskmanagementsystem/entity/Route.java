package stud.num.edu.mn.taskmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
/*
@author Bat-orgil
@date 2019-12-01
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROUTE", schema = "IMS")
@SequenceGenerator(name = "imsRouteSeq", sequenceName = "IMS.SEQ_ROUTE", allocationSize = 1, initialValue = 2000)
public class Route implements Serializable, Comparable<Route> {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "imsRouteSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ICON")
    private String icon;

    @Column(name = "ROUTE")
    private String route;

    @Column(name = "BREADCRUMB_PARENT_ID")
    private Long breadcrumbParentId;

    @Column(name = "MENU_PARENT_ID")
    private Long menuParentId;

    @Override
    public int compareTo(Route u) {
        if (getId() == null || u.getId() == null) {
            return 0;
        }
        return getId().compareTo(u.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route1 = (Route) o;
        return Objects.equals(route, route1.route);
    }
}
