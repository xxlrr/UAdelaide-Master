extern void A_init(void);
extern void B_init(void);
extern void A_input(struct pkt);
extern void B_input(struct pkt);
extern void A_output(struct msg);
extern void A_timerinterrupt(void);
extern int A_isNewAck(struct pkt);

/* included for extension to bidirectional communication */
#define BIDIRECTIONAL 0       /*  0 = A->B  1 =  A<->B */
extern void B_output(struct msg);
extern void B_timerinterrupt(void);
