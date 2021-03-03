package internet_store.core.service.product.paging;

import internet_store.core.persistence.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryOnePagingServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private CategoryOnePagingService pagingService;

    @Test
    public void onlyOnePage() {
        Mockito.when(productRepository.getLimitsProductsCategoryOne(6, 0)).thenReturn(new ArrayList<>());
        Mockito.when(productRepository.getCategoryOneCount()).thenReturn(1L);

        pagingService.startPaging();

        assertTrue(pagingService.isFirstPage());
        assertTrue(pagingService.isLastPage());
        assertEquals(1, pagingService.getCurrentPage());
        assertEquals(1, pagingService.getPagesQuantity());
    }

    @Test
    public void twoPages() {
        Mockito.when(productRepository.getLimitsProductsCategoryOne(6, 0)).thenReturn(new ArrayList<>());
        Mockito.when(productRepository.getCategoryOneCount()).thenReturn(7L);

        pagingService.startPaging();
        assertTrue(pagingService.isFirstPage());
        assertFalse(pagingService.isLastPage());
        assertEquals(1, pagingService.getCurrentPage());
        assertEquals(2, pagingService.getPagesQuantity());
    }

    @Test
    public void twoPages_Press_Next_ControlLastPage() {
        Mockito.when(productRepository.getLimitsProductsCategoryOne(6, 0)).thenReturn(new ArrayList<>());
        Mockito.when(productRepository.getCategoryOneCount()).thenReturn(7L);

        pagingService.startPaging();
        pagingService.nextPage(true);

        assertFalse(pagingService.isFirstPage());
        assertTrue(pagingService.isLastPage());
        assertEquals(2, pagingService.getCurrentPage());
        assertEquals(2, pagingService.getPagesQuantity());
    }

    @Test
    public void twoPages_Press_Prev_ControlFirstPage() {
        Mockito.when(productRepository.getLimitsProductsCategoryOne(6, 0)).thenReturn(new ArrayList<>());
        Mockito.when(productRepository.getCategoryOneCount()).thenReturn(7L);

        pagingService.startPaging();
        pagingService.nextPage(false);

        assertTrue(pagingService.isFirstPage());
        assertFalse(pagingService.isLastPage());
        assertEquals(0, pagingService.getCurrentPage());
        assertEquals(2, pagingService.getPagesQuantity());
    }

    @Test
    public void threePages_Press_Next() {
        Mockito.when(productRepository.getLimitsProductsCategoryOne(6, 0)).thenReturn(new ArrayList<>());
        Mockito.when(productRepository.getCategoryOneCount()).thenReturn(15L);

        pagingService.startPaging();
        pagingService.nextPage(true);

        assertFalse(pagingService.isFirstPage());
        assertFalse(pagingService.isLastPage());
        assertEquals(2, pagingService.getCurrentPage());
        assertEquals(3, pagingService.getPagesQuantity());
    }

    @Test
    public void threePages_Press_Next_TwoTimes_ControlLastPage() {
        Mockito.when(productRepository.getLimitsProductsCategoryOne(6, 0)).thenReturn(new ArrayList<>());
        Mockito.when(productRepository.getCategoryOneCount()).thenReturn(15L);

        pagingService.startPaging();
        pagingService.nextPage(true);
        pagingService.nextPage(true);

        assertFalse(pagingService.isFirstPage());
        assertTrue(pagingService.isLastPage());
        assertEquals(3, pagingService.getCurrentPage());
        assertEquals(3, pagingService.getPagesQuantity());
    }

    @Test
    public void threePages_Press_Prev_StartFromLastPage() {
        Mockito.when(productRepository.getLimitsProductsCategoryOne(6, 0)).thenReturn(new ArrayList<>());
        Mockito.when(productRepository.getCategoryOneCount()).thenReturn(15L);

        pagingService.startPaging();
        pagingService.nextPage(true);
        pagingService.nextPage(true);
        pagingService.nextPage(false);

        assertFalse(pagingService.isFirstPage());
        assertFalse(pagingService.isLastPage());
        assertEquals(2, pagingService.getCurrentPage());
        assertEquals(3, pagingService.getPagesQuantity());
    }

    @Test
    public void threePages_Press_Prev_TwoTimes_StartFromLastPage_ControlFirstPage() {
        Mockito.when(productRepository.getLimitsProductsCategoryOne(6, 0)).thenReturn(new ArrayList<>());
        Mockito.when(productRepository.getCategoryOneCount()).thenReturn(15L);

        pagingService.startPaging();
        pagingService.nextPage(true);
        pagingService.nextPage(true);
        pagingService.nextPage(false);
        pagingService.nextPage(false);

        assertTrue(pagingService.isFirstPage());
        assertFalse(pagingService.isLastPage());
        assertEquals(1, pagingService.getCurrentPage());
        assertEquals(3, pagingService.getPagesQuantity());
    }
}