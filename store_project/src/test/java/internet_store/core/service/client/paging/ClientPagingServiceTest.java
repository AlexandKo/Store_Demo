package internet_store.core.service.client.paging;

import internet_store.core.persistence.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientPagingServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientPagingService pagingService;

    @Test
    public void onlyOnePage() {
        Mockito.when(clientRepository.getLimitsClientRecords(4, 0)).thenReturn(new ArrayList<>());
        Mockito.when(clientRepository.count()).thenReturn(1L);

        pagingService.setRecordsCountOnPage(4);
        pagingService.startPaging();

        assertTrue(pagingService.isFirstPage());
        assertTrue(pagingService.isLastPage());
        assertEquals(1, pagingService.getCurrentPage());
        assertEquals(1, pagingService.getPagesQuantity());
    }

    @Test
    public void twoPages() {
        Mockito.when(clientRepository.getLimitsClientRecords(4, 0)).thenReturn(new ArrayList<>());
        Mockito.when(clientRepository.count()).thenReturn(7L);

        pagingService.setRecordsCountOnPage(4);
        pagingService.startPaging();

        assertTrue(pagingService.isFirstPage());
        assertFalse(pagingService.isLastPage());
        assertEquals(1, pagingService.getCurrentPage());
        assertEquals(2, pagingService.getPagesQuantity());
    }

    @Test
    public void twoPages_Press_Next_ControlLastPage() {
        Mockito.when(clientRepository.getLimitsClientRecords(4, 0)).thenReturn(new ArrayList<>());
        Mockito.when(clientRepository.count()).thenReturn(7L);

        pagingService.setRecordsCountOnPage(4);
        pagingService.startPaging();
        pagingService.nextPage(true);

        assertFalse(pagingService.isFirstPage());
        assertTrue(pagingService.isLastPage());
        assertEquals(2, pagingService.getCurrentPage());
        assertEquals(2, pagingService.getPagesQuantity());
    }

    @Test
    public void twoPages_Press_Prev_ControlFirstPage() {
        Mockito.when(clientRepository.getLimitsClientRecords(4, 0)).thenReturn(new ArrayList<>());
        Mockito.when(clientRepository.count()).thenReturn(7L);

        pagingService.setRecordsCountOnPage(4);
        pagingService.startPaging();
        pagingService.nextPage(false);

        assertTrue(pagingService.isFirstPage());
        assertFalse(pagingService.isLastPage());
        assertEquals(0, pagingService.getCurrentPage());
        assertEquals(2, pagingService.getPagesQuantity());
    }

    @Test
    public void threePages_Press_Next() {
        Mockito.when(clientRepository.getLimitsClientRecords(4, 0)).thenReturn(new ArrayList<>());
        Mockito.when(clientRepository.count()).thenReturn(10L);

        pagingService.setRecordsCountOnPage(4);
        pagingService.startPaging();
        pagingService.nextPage(true);

        assertFalse(pagingService.isFirstPage());
        assertFalse(pagingService.isLastPage());
        assertEquals(2, pagingService.getCurrentPage());
        assertEquals(3, pagingService.getPagesQuantity());
    }

    @Test
    public void threePages_Press_Next_TwoTimes_ControlLastPage() {
        Mockito.when(clientRepository.getLimitsClientRecords(4, 0)).thenReturn(new ArrayList<>());
        Mockito.when(clientRepository.count()).thenReturn(10L);

        pagingService.setRecordsCountOnPage(4);
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
        Mockito.when(clientRepository.getLimitsClientRecords(4, 0)).thenReturn(new ArrayList<>());
        Mockito.when(clientRepository.count()).thenReturn(10L);

        pagingService.setRecordsCountOnPage(4);
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
        Mockito.when(clientRepository.getLimitsClientRecords(4, 0)).thenReturn(new ArrayList<>());
        Mockito.when(clientRepository.count()).thenReturn(10L);

        pagingService.setRecordsCountOnPage(4);
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