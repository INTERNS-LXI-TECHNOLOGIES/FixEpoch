import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TimeSlotService } from 'app/entities/time-slot/time-slot.service';
import { ITimeSlot, TimeSlot } from 'app/shared/model/time-slot.model';
import { TimeSlotLabel } from 'app/shared/model/enumerations/time-slot-label.model';

describe('Service Tests', () => {
  describe('TimeSlot Service', () => {
    let injector: TestBed;
    let service: TimeSlotService;
    let httpMock: HttpTestingController;
    let elemDefault: ITimeSlot;
    let expectedResult: ITimeSlot | ITimeSlot[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TimeSlotService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TimeSlot(0, 'AAAAAAA', 'AAAAAAA', TimeSlotLabel.MORNING);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TimeSlot', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TimeSlot()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TimeSlot', () => {
        const returnedFromService = Object.assign(
          {
            startTime: 'BBBBBB',
            endTime: 'BBBBBB',
            timeSlotLabel: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TimeSlot', () => {
        const returnedFromService = Object.assign(
          {
            startTime: 'BBBBBB',
            endTime: 'BBBBBB',
            timeSlotLabel: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TimeSlot', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
