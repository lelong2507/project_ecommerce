    package com.example.ProjectBE.entities;

    import java.util.Date;
    import java.util.List;

    import org.springframework.format.annotation.DateTimeFormat;

    import jakarta.persistence.CascadeType;
    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.FetchType;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.JoinColumn;
    import jakarta.persistence.ManyToOne;
    import jakarta.persistence.OneToMany;
    import jakarta.persistence.Table;
    import lombok.AccessLevel;
    import lombok.Data;
    import lombok.experimental.FieldDefaults;

    @Entity
    @Table(name = "orders")
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Data
    public class Order {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_order")
        int idOrder;
        @Column(name = "date_order")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date dateOrder;
        @Column(name = "vat")
        double vat;
        @ManyToOne
        @JoinColumn(name = "user_id")
        User user;
        @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        List<OrderDetail> orderDetails;
    }
