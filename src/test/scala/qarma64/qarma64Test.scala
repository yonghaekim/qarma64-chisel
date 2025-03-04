import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
//import chisel3.iotesters
//import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

//Qarma64 basic tester

/*
//tester for permute_tweak
class PermuteTweakTest extends AnyFlatSpec with ChiselScalatestTester{
    behavior of "PermuteTweak"
    it should "Permute the hex digits of a tweak" in {
        test(new qarma64.PermuteTweak){c =>
            c.io.tweak.poke("hBE5466CF34E90C6C".U)
            c.clock.step()
           // c.clock.step()
            c.io.permuted_tweak.expect("hc66cbe54f0c634e9".U)
            //println("Last Output value :" + c.io.cipher.peek().litValue)

        }
    }
}

//tester for permute_state
class PermuteStateTest extends AnyFlatSpec with ChiselScalatestTester{
    behavior of "PermuteState"
    it should "Permute the hex digits of a state" in {
        test(new qarma64.PermuteState){c =>
            c.io.state.poke("hBE5466CF34E90C6C".U)
            c.io.inverse.poke(false.B)
            c.clock.step()
            c.io.permuted_state.expect("hb9ccee0f6643c645".U)

            c.io.state.poke("hBE5466CF34E90C6C".U)
            c.io.inverse.poke(true.B)
            c.clock.step()
            c.io.permuted_state.expect("hb6cec35f966ec440".U)
            //println("Last Output value :" + c.io.cipher.peek().litValue)

        }
    }
}

class TweakLFSRTest extends AnyFlatSpec with ChiselScalatestTester{
    behavior of "TweakLFSR"
    it should "Do an LFSR operation on an input tweak" in {
        test(new qarma64.TweakLFSR){c =>
            c.io.tweak.poke("hBE5466CF34E90C6C".U)
            c.clock.step()
            c.clock.step()
            c.io.lfsr.expect("h5f52b6cf14ec066c".U)
            //println("Last Output value :" + c.io.cipher.peek().litValue)

        }
    }
}

class CalcTweakTest extends AnyFlatSpec with ChiselScalatestTester{
    behavior of "CalcTweak"
    it should "Do r rounds of permute and LFSR" in {
        test(new CalcTweak()){c =>
            c.io.tweak.poke("hBE5466CF34E90C6C".U)
            //c.io.r.poke(5.U)
            for (i <- 0 until 1) // 4 clock cycles
            {
               c.clock.step()
            }
            //c.clock.step()
            c.io.tweak_r.expect("h6a0a772dab3a691e".U)
            //println("Last Output value :" + c.io.cipher.peek().litValue)

        }
    }
}

class CalcRoundTweakeyTest extends AnyFlatSpec with ChiselScalatestTester{
    behavior of "CalcRoundTweakey"
    it should "Use CalcTweak and do some xor stuff" in {
        test(new CalcRoundTweakey()){c =>
            c.io.tweak.poke("hBE5466CF34E90C6C".U)
            c.io.k0.poke("hec2802d4e0a488e9".U)
            c.io.backwards.poke(false.B)

            for (i <- 0 until 1)
            {
               c.clock.step()
            }
            c.io.output.expect("h387613367f77ed9b".U)

            c.io.tweak.poke("hBE5466CF34E90C6C".U)
            c.io.k0.poke("hec2802d4e0a488e9".U)
            c.io.backwards.poke(true.B)
            for (i <- 0 until 1)
            {
               c.clock.step()
            }
            c.io.output.expect("hf8da3a81b60bbd46".U)
        }
    }
}


class RoundTest extends AnyFlatSpec with ChiselScalatestTester{
    behavior of "Round"
    it should "Do the round operation" in {
        test(new Round()){c =>
            c.io.state.poke("hBE5466CF34E90C6C".U)
            c.io.tweakey.poke("h477d469dec0b8762".U)
            c.io.backwards.poke(false.B)
            for (i <- 0 until 2)
            {
               c.clock.step()
            }
            c.io.round_state.expect("h0b8b9d7955f19279".U)

            c.io.state.poke("hBE5466CF34E90C6C".U)
            c.io.tweakey.poke("h477d469dec0b8762".U)
            c.io.backwards.poke(true.B)
            for (i <- 0 until 2)
            {
               c.clock.step()
            }
            c.io.round_state.expect("hf1987a7196b7778b".U)
        }
    }
}

class MiddleRoundTest extends AnyFlatSpec with ChiselScalatestTester{
    behavior of "MiddleRound"
    it should "Do the middleround operation" in {
        test(new MiddleRound()){c =>
            c.io.state.poke("hbe5466cf34e90c6c".U)
            c.io.k1.poke("hec2802d4e0a488e9".U)
            for (i <- 0 until 2)
            {
               c.clock.step()
            }
            c.io.middleround_state.expect("h3491c4be2f84d178".U)
        }
    }
}
*/

class Qarma64Test extends AnyFlatSpec with ChiselScalatestTester{
    behavior of "Qarma64"
    it should "Full qarma64 implementation with 5 rounds using plaintext fb623599da6e8127 and tweak 477d469dec0b8762" in {
        test(new Qarma64()){c =>
            c.io.plaintext.poke("hfb623599da6e8127".U)
            c.io.tweak.poke("h477d469dec0b8762".U)
            c.io.w0.poke("h84be85ce9804e94b".U)
            c.io.k0.poke("hec2802d4e0a488e9".U)
            c.io.encryption.poke(true.B)
            for (i <- 0 until 2)
            {
               c.clock.step()
            }
            c.io.ciphertext.expect("h3ee99a6c82af0c38".U)
        }
    }
    it should "Full qarma64 implementation with 5 rounds using plaintext fb112809da6e8127 and tweak 477d469dec0b8762" in {
        test(new Qarma64()){c =>
            c.io.plaintext.poke("hfb112809da6e8127".U)
            c.io.tweak.poke("h477d469dec0b8762".U)
            c.io.w0.poke("h84be85ce9804e94b".U)
            c.io.k0.poke("hec2802d4e0a488e9".U)
            c.io.encryption.poke(true.B)
            for (i <- 0 until 2)
            {
               c.clock.step()
            }
            c.io.ciphertext.expect("hc26ebceccb9b6e3d".U)
        }
    }
    it should "Full qarma64 implementation with 5 rounds using plaintext fb623599da611280 and tweak 477d469dec0b8762" in {
        test(new Qarma64()){c =>
            c.io.plaintext.poke("hfb623599da611280".U)
            c.io.tweak.poke("h477d469dec0b8762".U)
            c.io.w0.poke("h84be85ce9804e94b".U)
            c.io.k0.poke("hec2802d4e0a488e9".U)
            c.io.encryption.poke(true.B)
            for (i <- 0 until 2)
            {
               c.clock.step()
            }
            c.io.ciphertext.expect("h6b21eb8883a8b1aa".U)
        }
    }
    it should "Full qarma64 implementation with 5 rounds using plaintext fb623599da6e8127 and tweak 4711280dec0b8762" in {
        test(new Qarma64()){c =>
            c.io.plaintext.poke("hfb623599da6e8127".U)
            c.io.tweak.poke("h4711280dec0b8762".U)
            c.io.w0.poke("h84be85ce9804e94b".U)
            c.io.k0.poke("hec2802d4e0a488e9".U)
            c.io.encryption.poke(true.B)
            for (i <- 0 until 2)
            {
               c.clock.step()
            }
            c.io.ciphertext.expect("hdd4c533af8ddfb77".U)
        }
    }
    it should "Full qarma64 implementation with 5 rounds using plaintext fb623599da6e8127 and tweak 477d469dec011280" in {
        test(new Qarma64()){c =>
            c.io.plaintext.poke("hfb623599da6e8127".U)
            c.io.tweak.poke("h477d469dec011280".U)
            c.io.w0.poke("h84be85ce9804e94b".U)
            c.io.k0.poke("hec2802d4e0a488e9".U)
            c.io.encryption.poke(true.B)
            for (i <- 0 until 2)
            {
               c.clock.step()
            }
            c.io.ciphertext.expect("hf5134e936f5c078d".U)
        }
    }
    it should "Full qarma64 implementation with 5 rounds using ciphertext f5134e936f5c078d and tweak 477d469dec011280" in {
        test(new Qarma64()){c =>
            c.io.plaintext.poke("hf5134e936f5c078d".U)
            c.io.tweak.poke("h477d469dec011280".U)
            c.io.w0.poke("h84be85ce9804e94b".U)
            c.io.k0.poke("hec2802d4e0a488e9".U)
            c.io.encryption.poke(false.B)
            for (i <- 0 until 2)
            {
               c.clock.step()
            }
            c.io.ciphertext.expect("hfb623599da6e8127".U)
        }
    }
    it should "Full qarma64 implementation with 5 rounds using ciphertext dd4c533af8ddfb77 and tweak 4711280dec0b8762" in {
        test(new Qarma64()){c =>
            c.io.plaintext.poke("hdd4c533af8ddfb77".U)
            c.io.tweak.poke("h4711280dec0b8762".U)
            c.io.w0.poke("h84be85ce9804e94b".U)
            c.io.k0.poke("hec2802d4e0a488e9".U)
            c.io.encryption.poke(false.B)
            for (i <- 0 until 2)
            {
               c.clock.step()
            }
            c.io.ciphertext.expect("hfb623599da6e8127".U)
        }
    }
    it should "Full qarma64 implementation with 5 rounds using ciphertext 6b21eb8883a8b1aa and tweak 477d469dec0b8762" in {
        test(new Qarma64()){c =>
            c.io.plaintext.poke("h6b21eb8883a8b1aa".U)
            c.io.tweak.poke("h477d469dec0b8762".U)
            c.io.w0.poke("h84be85ce9804e94b".U)
            c.io.k0.poke("hec2802d4e0a488e9".U)
            c.io.encryption.poke(false.B)
            for (i <- 0 until 2)
            {
               c.clock.step()
            }
            c.io.ciphertext.expect("hfb623599da611280".U)
        }
    }
    it should "Full qarma64 implementation with 5 rounds using ciphertext c26ebceccb9b6e3d and tweak 477d469dec0b8762" in {
        test(new Qarma64()){c =>
            c.io.plaintext.poke("hc26ebceccb9b6e3d".U)
            c.io.tweak.poke("h477d469dec0b8762".U)
            c.io.w0.poke("h84be85ce9804e94b".U)
            c.io.k0.poke("hec2802d4e0a488e9".U)
            c.io.encryption.poke(false.B)
            for (i <- 0 until 2)
            {
               c.clock.step()
            }
            c.io.ciphertext.expect("hfb112809da6e8127".U)
        }
    }
    it should "Full qarma64 implementation with 5 rounds using ciphertext 3ee99a6c82af0c38 and tweak 477d469dec0b8762" in {
        test(new Qarma64()){c =>
            c.io.plaintext.poke("h3ee99a6c82af0c38".U)
            c.io.tweak.poke("h477d469dec0b8762".U)
            c.io.w0.poke("h84be85ce9804e94b".U)
            c.io.k0.poke("hec2802d4e0a488e9".U)
            c.io.encryption.poke(false.B)
            for (i <- 0 until 2)
            {
               c.clock.step()
            }
            c.io.ciphertext.expect("hfb623599da6e8127".U)
        }
    }
}


class CalcTweakTest_0Rounds extends AnyFlatSpec with ChiselScalatestTester{
    behavior of "CalcTweak_rounds0"
    it should "Do r rounds of permute and LFSR" in {
        test(new CalcTweak_5rounds()){c =>
            c.io.tweak.poke("hBE5466CF34E90C6C".U)
            //c.io.r.poke(5.U)
            for (i <- 0 until 1) // 4 clock cycles
            {
               c.clock.step()
            }
            //c.clock.step()
            c.io.tweak_r.expect("h6a0a772dab3a691e".U)
            //println("Last Output value :" + c.io.cipher.peek().litValue)

        }
    }
}

class CalcRoundTweakeyTest_5rounds extends AnyFlatSpec with ChiselScalatestTester{
    behavior of "CalcRoundTweakey"
    it should "Use CalcTweak and do some xor stuff" in {
        test(new CalcRoundTweakey_0rounds()){c =>
            /*c.io.tweak.poke("hBE5466CF34E90C6C".U)
            c.io.k0.poke("h527c641bd44d8485".U)
            c.io.backwards.poke(false.B)

            for (i <- 0 until 1)
            {
               c.clock.step()
            }
            c.io.output.expect("hec2802d4e0a488e9".U)*/

            c.io.tweak.poke("hBE5466CF34E90C6C".U)
            c.io.k0.poke("h527c641bd44d8485".U)
            c.io.backwards.poke(true.B)
            for (i <- 0 until 1)
            {
               c.clock.step()
            }
            c.io.output.expect("h2c842b6329d8d834".U)
        }
    }
}

class RoundTest extends AnyFlatSpec with ChiselScalatestTester{
    behavior of "Round"
    it should "Do the round operation" in {
        test(new Round_0rounds()){c =>
            c.io.state.poke("hBE5466CF34E90C6C".U)
            c.io.tweakey.poke("h527c641bd44d8485".U)
            c.io.backwards.poke(false.B)
            for (i <- 0 until 2)
            {
               c.clock.step()
            }
            c.io.round_state.expect("h1d2602c910396614".U)

        c.io.state.poke("hBE5466CF34E90C6C".U)
            c.io.tweakey.poke("h527c641bd44d8485".U)
            c.io.backwards.poke(true.B)
            for (i <- 0 until 2)
            {
               c.clock.step()
            }
            c.io.round_state.expect("h2385ecce7d598908".U)
        }
    }
}
/*
class MixColumnsTest extends AnyFlatSpec with ChiselScalatestTester{
    behavior of "MixColumns Module"
    it should "Mix each column in a set manner" in {
        test(new qarma64.MixColumns){c =>
            c.io.state.poke("hbe5466cf34e90c6c".U)

            for (i <- 0 until 4) // 4 clock cycles
            {
               c.clock.step()
            }

            c.io.mixed_state.expect("h04e016e82e078c44".U)
            //println("Last Output value :" + c.io.cipher.peek().litValue)

        }
    }
}*/
