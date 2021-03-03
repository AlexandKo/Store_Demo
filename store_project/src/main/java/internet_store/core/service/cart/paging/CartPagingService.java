package internet_store.core.service.cart.paging;

import internet_store.core.domain.ProductInCart;
import internet_store.core.persistence.CartRepository;
import internet_store.core.service.session.SessionService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartPagingService {
    private final int PAGE_OFFSET = 1;
    private final int FIRST_PAGE = 1;
    private final int START_FROM_FIRST_RECORD = 0;
    @Autowired
    private CartRepository CartRepository;
    @Autowired
    private SessionService sessionService;
    @Getter
    private int recordsCountOnPage;
    private int startRecordOffset;
    @Getter
    private int pagesQuantity;
    @Getter
    private int currentPage;
    @Getter
    private boolean isFirstPage;
    @Getter
    private boolean isLastPage;
    @Getter
    private List<ProductInCart> listOnePage;

    public void startPaging() {
        final int LIMIT_RECORDS_ON_ONE_PAGE = 4;
        isFirstPage = true;
        isLastPage = false;
        startRecordOffset = START_FROM_FIRST_RECORD;
        recordsCountOnPage = LIMIT_RECORDS_ON_ONE_PAGE;
        currentPage = FIRST_PAGE;
        calculatePagesQuantity();
        listOnePage = CartRepository.getLimitsCartRecords(sessionService.getSessionId(), recordsCountOnPage, startRecordOffset);
        if ((startRecordOffset + recordsCountOnPage) >= CartRepository.countProductInCart(sessionService.getSessionId())) {
            isLastPage = true;
        }
    }

    public void nextPage(boolean pagingDirection) {
        if (pagingDirection) {
            nextPage();
        } else {
            prevPage();
        }
    }

    private void nextPage() {
        if (currentPage + PAGE_OFFSET >= pagesQuantity) {
            currentPage++;
            startRecordOffset += recordsCountOnPage;
            isLastPage = true;
            isFirstPage = false;
        } else {
            currentPage++;
            startRecordOffset += recordsCountOnPage;
            isLastPage = false;
            isFirstPage = false;
        }
        listOnePage = CartRepository.getLimitsCartRecords(sessionService.getSessionId(), recordsCountOnPage, startRecordOffset);
    }

    private void prevPage() {
        if (currentPage - PAGE_OFFSET <= FIRST_PAGE) {
            currentPage--;
            startRecordOffset = START_FROM_FIRST_RECORD;
            isFirstPage = true;
            isLastPage = false;
        } else {
            currentPage--;
            startRecordOffset -= recordsCountOnPage;
            isFirstPage = false;
            isLastPage = false;
        }
        listOnePage = CartRepository.getLimitsCartRecords(sessionService.getSessionId(), recordsCountOnPage, startRecordOffset);
    }

    private void calculatePagesQuantity() {
        final int NO_EXTRA_PAGE = 0;

        long searchResultCount = CartRepository.countProductInCart(sessionService.getSessionId());

        if (isAllRecordsCanSetOnePage(searchResultCount)) return;

        if ((searchResultCount % recordsCountOnPage) == NO_EXTRA_PAGE) {
            pagesQuantity = (int) searchResultCount / recordsCountOnPage;
        } else {
            pagesQuantity = ((int) searchResultCount / recordsCountOnPage) + PAGE_OFFSET;
        }
    }

    private boolean isAllRecordsCanSetOnePage(long searchResultCount) {
        final int ONLY_ONE_PAGE = 1;
        if (searchResultCount < recordsCountOnPage) {
            pagesQuantity = ONLY_ONE_PAGE;
            return true;
        }
        return false;
    }
}