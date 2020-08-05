package my.demo.bookmark.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor(force = true) // all final fields are initialized with 0 / false / null
@RequiredArgsConstructor(onConstructor=@__({@NotNull}))
public class Privilege {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    @NotNull
    private final String name;
 
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}
