package com.javarush.entity;

import com.javarush.converters.RatingConverter;
import com.javarush.converters.YearConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "film")
@Data
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", name = "description")
    @Type(type = "text")
    private String description;

    @Column(name = "release_year", columnDefinition = "year")
    @Convert(converter = YearConverter.class)
    private Year year;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "language_id", nullable = false)
    private Language languageId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "original_language_id")
    private Language originalLanguageId;

    @Column(name = "rental_duration", nullable = false)
    @ColumnDefault("3")
    private Byte rentalDuration;

    @Column(name = "rental_rate", nullable = false)
    @ColumnDefault("4.99")
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @Column(name = "replacement_cost", nullable = false)
    @ColumnDefault("19.99")
    private BigDecimal replacementCost;


    @Column(name = "rating", columnDefinition = "enum")
    @Convert(converter = RatingConverter.class)
    private Rating rating;


    @Column(name = "special_features", columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private String specialFeatures;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
    @ToString.Exclude
    private Set<Actor> actors;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    @ToString.Exclude
    private Set<Category> categories;

    public void setSpecialFeatures(Set<Feature> features) {
        if (features == null || features.isEmpty()) {
            specialFeatures = null;
        } else {
          specialFeatures = features.stream()
                    .map(Feature::getValue)
                    .collect(Collectors.joining(","));
        }
    }

    public Set<Feature> getSpecialFeatures() {
        if (specialFeatures == null || specialFeatures.isEmpty()) {
            return null;
        }
        Set<Feature> set = new HashSet<>();
        String[] split = specialFeatures.split(",");
        for (String s : split) {
            set.add(Feature.getFeatureByValue(s));
        }
        set.remove(null);
        return set;
    }
}
