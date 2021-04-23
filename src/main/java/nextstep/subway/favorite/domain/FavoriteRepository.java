package nextstep.subway.favorite.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByMemberId(Long memberId);

    @Query(value = "SELECT * FROM favorite WHERE favorite.member_id = ?1",
            countQuery = "SELECT count(*) FROM favorite WHERE favorite.member_id = ?1",
            nativeQuery = true)
    Page<Favorite> findAll(Long memberId, Pageable pageable);
}
