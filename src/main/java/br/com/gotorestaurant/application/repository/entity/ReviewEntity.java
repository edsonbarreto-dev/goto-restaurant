package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;




@Entity(name = "Review")
@Table(name = "reviews", schema = "gotorestaurant")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SocialMediaEntity socialMediaEntity;

    private String question;

    private int vote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}
