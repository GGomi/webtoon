package com.essri.webtoon.batch.job;

//@Slf4j
//@RequiredArgsConstructor
//@Configuration
public class InsertWebtoonList {
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//    private final Crawling crawling;
//    private final ToonRepository toonRepository;
//
//    @Bean
//    public Job itsJob() {
//        return jobBuilderFactory.get("itsJob")
//                                .start(loadStep())
//                                .build();
//    }
//
//    @Bean
//    public Step loadStep() {
//        return stepBuilderFactory.get("itsJob")
//                            .tasklet((contribution, chunkContext) -> {
//                                toonRepository.deleteAll();
//                                List<Toons> list =  crawling.getNaverToonData();
//                                toonRepository.saveAll(list);
//                                log.debug(">>>>>> batch Running!!!!! ");
//                                return RepeatStatus.FINISHED;
//                            }).build();
//    }
}
