package internet_store.core.service.ordering.paging;

import internet_store.core.domain.Order;
import internet_store.core.persistence.OrderRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderControlPagingService {
    private final int PAGE_OFFSET = 1;
    private final int FIRST_PAGE = 1;
    private final int START_FROM_FIRST_RECORD = 0;
    @Autowired
    private OrderRepository orderRepository;
    @Getter
    @Setter
    @Value("${order-records-on-page}")
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
    private List<Order> listOnePage;

    public void startPaging() {
        isFirstPage = true;
        isLastPage = false;
        startRecordOffset = START_FROM_FIRST_RECORD;
        currentPage = FIRST_PAGE;
        listOnePage = orderRepository.getLimitsProductsRecords(recordsCountOnPage, startRecordOffset);
        calculatePagesQuantity();
        if ((startRecordOffset + recordsCountOnPage) >= orderRepository.countUniqOrders()) {
            isLastPage = true;
        }
    }

    public List<Order> refreshTableContent() {
        return orderRepository.getLimitsProductsRecords(recordsCountOnPage, startRecordOffset);
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
        listOnePage = orderRepository.getLimitsProductsRecords(recordsCountOnPage, startRecordOffset);
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
        listOnePage = orderRepository.getLimitsProductsRecords(recordsCountOnPage, startRecordOffset);
    }

    private void calculatePagesQuantity() {
        final int NO_EXTRA_PAGE = 0;

        long searchResultCount = orderRepository.countUniqOrders();

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