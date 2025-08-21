package com.traffictest.board.service;

import com.traffictest.entity.InfinityComment;
import com.traffictest.entity.InfinityCommentRepository;
import com.traffictest.entity.User;
import com.traffictest.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InfinityCommentService {

    private final InfinityCommentRepository infinityCommentRepository;
    private final UserRepository userRepository;

    // 45000 = 1sec
    public List<InfinityComment> findAll() {
        return infinityCommentRepository.findCommentHierarchy();
    }

    // suppose 1000;
    public void init(int count) {
        int counter = 0;
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(User.builder()
                    .username("user" + i)
                    .build());
        }
        userRepository.saveAll(users);
        // 5단계 댓글 생성
        // need fix
        while (counter < count) {
            // make 20 depth;
            List<InfinityComment> infinityComments = new ArrayList<>();
            // 첫 comment
            InfinityComment comment = InfinityComment.builder()
                    .content("comment" + counter)
                    .writer(users.get(counter++ % 10))
                    .build();
            infinityComments.add(comment);
            for (int i = 0; i < 99; i++) {
                comment = InfinityComment.builder()
                        .content("comment" + counter)
                        .parent(comment)
                        .writer(users.get(counter++ % 10))
                        .build();
                infinityComments.add(comment);
            }
            infinityCommentRepository.saveAll(infinityComments);
            infinityComments.clear();
        }
    }

    public void enhancedInit(int count) {
        int counter = 0;
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(User.builder()
                    .username("user" + i)
                    .build());
        }
        userRepository.saveAll(users);

        List<InfinityComment> buffer = new ArrayList<>();
        InfinityComment comment = InfinityComment.builder()
                .content("commnet =" + counter)
                .writer(users.get(0))
                .build();
        counter++;
        buffer.add(comment);
        List<InfinityComment> parents = new ArrayList<>();
        List<InfinityComment> childrens = new ArrayList<>();
        parents.add(comment);
        while (counter < count) {
            for(InfinityComment parent : parents){
                for(int i=0;i<parents.size() * 2;i++){
                    comment = InfinityComment.builder()
                            .content("commnet =" + counter)
                            .parent(parent)
                            .writer(users.get(counter++ % 10))
                            .build();
                    childrens.add(comment);
                    buffer.add(comment);

                    if( buffer.size() == 1000){
                        infinityCommentRepository.saveAll(buffer);
                        buffer.clear();
                    }
                    if(counter == count){
                        infinityCommentRepository.saveAll(buffer);
                        return;
                    }
                }
            }
            parents.clear();
            parents = childrens;
            childrens = new ArrayList<>();
        }

    }

}
