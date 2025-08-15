package com.traffictest.board.service;

import com.traffictest.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TwoDepthReplyService {
    private final TwoDepthCommentRepository repository;
    private final UserRepository userRepository;
    private final TwoDepthCommentRepository twoDepthCommentRepository;

    public List<TwoDepthComment> findAll() {
        return repository.findAll();
    }

    public void init(int count) {
        int counter = 0;
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(User.builder()
                    .username("user " + i)
                    .build());
        }
        userRepository.saveAll(users);

        while (counter < count) {
            List<TwoDepthReply> replies = new ArrayList<>();

            TwoDepthComment comment = TwoDepthComment.builder()
                    .content("comment" + counter)
                    .writer(users.get(counter++ % 10))
                    .build();
            for (int i = 0; i < 49; i++) {
                TwoDepthReply reply = TwoDepthReply.builder()
                        .content("reply" + counter)
                        .writer(users.get(counter++ % 10))
                        .build();
                replies.add(reply);
            }
            comment.setReplyList(replies);
            twoDepthCommentRepository.save(comment);
        }
    }
}